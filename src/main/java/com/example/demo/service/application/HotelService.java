package com.example.demo.service.application;

import com.example.demo.controller.hotel.dto.HotelCreateRequestDto;
import com.example.demo.controller.hotel.dto.HotelDetailResponseDto;
import com.example.demo.controller.hotel.dto.HotelResponseDto;
import com.example.demo.controller.hotel.dto.RoomAvailabilityDto;
import com.example.demo.controller.room.dto.AvailableRoomRawDto;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.hotel.RoomDateRepository;
import com.example.demo.repository.hotel.RoomTypeRepository;
import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomDate;
import com.example.demo.repository.hotel.entity.RoomType;
import com.example.demo.service.domain.PricingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomDateRepository roomDateRepository;
    private final PricingService pricingService;



    @Transactional(readOnly = true)
    public HotelResponseDto findById(Integer id){
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("호텔이 DB내 존재하지 않습니다. 호텔 ID = "+ id));

        return HotelResponseDto.from(hotel);
    }

    @Transactional
    public HotelResponseDto save(HotelCreateRequestDto requestDto){
        Hotel hotel = Hotel.create(
                requestDto.getHotelName(),
                requestDto.getAddress(),
                requestDto.getHotelRating(),
                requestDto.getCity(),
                requestDto.getDescription(),
                requestDto.getCheckInTime(),
                requestDto.getCheckOutTime()
        );

        Hotel created = hotelRepository.save(hotel);
        return HotelResponseDto.from(created);

    }

    public HotelDetailResponseDto getHotelDetail(Integer hotelId, LocalDate date) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("호텔을 찾을 수 없습니다. id=" + hotelId));

        // 특정 호텔의 모든 룸타입 + date 기준 예약 가능 여부 조회
        List<AvailableRoomRawDto> rawList = roomDateRepository.findAllRoomByHotelAndDate(hotelId, date);

        // 룸타입별 DTO 변환 ...하 힘들당
        List<RoomAvailabilityDto> roomDto = rawList.stream()
                .map(raw -> RoomAvailabilityDto.from(
                        raw.getRoomType(),
                        raw.getAvailable(),
                        raw.getAvailable()
                                ? pricingService.calculate(date, raw.getRoomType())
                                : null
                ))
                .toList();

        return HotelDetailResponseDto.from(hotel, roomDto);
    }


}
