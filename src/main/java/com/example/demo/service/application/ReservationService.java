package com.example.demo.service.application;

import com.example.demo.controller.reservation.dto.ReservationCreateRequestDto;
import com.example.demo.controller.reservation.dto.ReservationResponseDto;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.hotel.RoomDateRepository;
import com.example.demo.repository.hotel.RoomNumberRepository;
import com.example.demo.repository.hotel.RoomTypeRepository;
import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomDate;
import com.example.demo.repository.hotel.entity.RoomNumber;
import com.example.demo.repository.hotel.entity.RoomType;
import com.example.demo.repository.reservation.BookingRepository;
import com.example.demo.repository.reservation.ReservationRepository;
import com.example.demo.repository.reservation.entity.Booking;
import com.example.demo.repository.reservation.entity.Reservation;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import com.example.demo.service.domain.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomNumberRepository roomNumberRepository;
    private final RoomDateRepository roomDateRepository;
    private final BookingRepository bookingRepository;
    private final PricingService pricingService;

    @Transactional
    public ReservationResponseDto save(ReservationCreateRequestDto request) {
        // 날짜, 호텔, 객실타입, 호수, 유저아이디
        // 해당 날짜에 해당 호텔의 객실 타입에 방이 존재하지 않으면 예약 불가능 에러
        // 잘못된 호텔아이디 / 객실타입아이디 / 호수일 때 에러

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("not exists user"));
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("not exists hotel"));
        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("not exists roomType"));
        RoomNumber roomNumber = roomNumberRepository.findById(request.getRoomNumberId())
                .orElseThrow(() -> new RuntimeException("not exists roomNumber"));

        // 호텔 안에 속하는 객실타입
        if (!roomType.getHotel().getId().equals(request.getHotelId())) {
            throw new IllegalArgumentException("Invalid room type");
        }
        // 객실타입 안에 속하는 호수
        if (!roomNumber.getRoomType().getId().equals(request.getRoomTypeId())) {
            throw new IllegalArgumentException("Invalid room number");
        }

        if (roomType.getCapacity() < request.getGuestCount()) {
            throw new IllegalArgumentException("over capacity guestCount");
        }

        // 예약 가능
        // 재고 확인
        List<RoomDate> dates = roomDateRepository.findByRoomIdAndDateBetween(
                request.getRoomNumberId(),
                request.getCheckInDate(),
                request.getCheckOutDate().minusDays(1)
        );
        if (dates.isEmpty()) {
            throw new IllegalArgumentException("No room information exists for the selected period");
        }
//        dates.forEach(d -> System.out.println(d.getDate() + " : " + d.isAvailable()));

        boolean isAvailable = dates.stream().allMatch(RoomDate::isAvailable);
        if (!isAvailable) {
            throw new IllegalArgumentException("The room is already booked");
        }

        double totalPrice = request.getCheckInDate()
                .datesUntil(request.getCheckOutDate())
                .mapToDouble(date -> pricingService.calculate(date, roomType))
                .sum();

        // booking 임의 생성
        Booking booking = Booking.create(user, totalPrice);
        booking = bookingRepository.save(booking);
        // 예약 생성
        Reservation reservation = Reservation.create(
                roomNumber,
                request.getCheckInDate(),
                request.getCheckOutDate(),
                request.getGuestCount(),
                false,
                null,
                totalPrice
        );
        reservation.assignBooking(booking);

        Reservation created = reservationRepository.save(reservation);
        dates.forEach(roomDate -> roomDate.setAvailable(false));
        roomDateRepository.saveAll(dates);
        return ReservationResponseDto.from(created);
    }


    @Transactional
    public void delete(Integer reservationId, Integer userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("not exists reservation"));

        // user 객체 자체는 필요 없는 거 같고 단순히 존재하는 userId인지 확인용으로라도 남겨야하나?
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("not exists user"));

        // 예약자 본인 확인
        // reservatui -> booking -> user로 검사?
        if (!reservation.getBooking().getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You are not the owner of this reservation.");
        }

        // 날짜 당일 or 지난 예약 확인
        if (!reservation.getCheckInDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("You cannot cancel reservations for today or past dates");
        }

        // 예약 취소한 날짜 Available 풀어주기
        List<RoomDate> dates = roomDateRepository.findByRoomIdAndDateBetween(
                reservation.getRoomNumber().getId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate().minusDays(1)
        );
        dates.forEach(roomDate -> roomDate.setAvailable(true));
        roomDateRepository.saveAll(dates);

        reservationRepository.deleteById(reservationId);
        System.out.println(reservationId);
    }
}