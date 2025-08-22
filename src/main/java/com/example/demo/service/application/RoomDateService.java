package com.example.demo.service.application;

import com.example.demo.controller.hotel.dto.HotelSimpleResponseDto;
import com.example.demo.controller.room.dto.AvailableRoomRawDto;
import com.example.demo.controller.room.dto.AvailableRoomResponseDto;
import com.example.demo.controller.room.dto.RoomDateCreateRequestDto;
import com.example.demo.controller.room.dto.RoomDateResponseDto;
import com.example.demo.repository.hotel.RoomDateRepository;
import com.example.demo.repository.hotel.RoomNumberRepository;
import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomDate;
import com.example.demo.repository.hotel.entity.RoomNumber;
import com.example.demo.service.domain.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomDateService {
    private final RoomDateRepository roomDateRepository;
    private final RoomNumberRepository roomNumberRepository;
    private final PricingService pricingService;

    @Transactional(readOnly = true)
    public List<RoomDateResponseDto> findAll() {
        return roomDateRepository.findAll().stream()
                .map(RoomDateResponseDto::from)
                .toList();
    }

    @Transactional
    public RoomDateResponseDto save(RoomDateCreateRequestDto requestDto) {
        RoomNumber roomNumber = roomNumberRepository.findById(requestDto.getRoomNumberId())
                .orElseThrow(() -> new RuntimeException("해당 방이 존재하지 않습니다. id=" + requestDto.getRoomNumberId()));

        RoomDate roomDate = RoomDate.create(
                requestDto.isAvailable(),
                requestDto.getDate(),
                roomNumber
        );

        return RoomDateResponseDto.from(roomDateRepository.save(roomDate));
    }

    @Transactional(readOnly = true)
    public List<HotelSimpleResponseDto> findHotelSimpleByDate(LocalDate date) {
        List<AvailableRoomRawDto> rawList = roomDateRepository.findAvailableRoomByDate(date);

        // 호텔별로 최소가를 추출
        Map<Hotel, Optional<AvailableRoomRawDto>> groupedByHotel = rawList.stream()
                .collect(Collectors.groupingBy(
                        AvailableRoomRawDto::getHotel,
                        Collectors.minBy(Comparator.comparing(
                                raw -> pricingService.calculate(date, raw.getRoomType())
                        ))
                ));


        return groupedByHotel.entrySet().stream()
                .map(entry -> {

                    Hotel hotel = entry.getKey();

                    Optional<AvailableRoomRawDto> maybeRoom = entry.getValue();

                    return maybeRoom.map((raw) -> {
                                Double minPrice = pricingService.calculate(date, raw.getRoomType());
                                return HotelSimpleResponseDto.available(
                                        hotel,
                                        raw.getRoomType(),
                                        minPrice
                                );
                            }
                    ).orElse(HotelSimpleResponseDto.unavailable(hotel, null, null));
                }).toList();
    }


}
