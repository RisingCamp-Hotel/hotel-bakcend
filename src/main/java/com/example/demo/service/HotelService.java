package com.example.demo.service;

import com.example.demo.controller.hotel.dto.HotelCreateRequestDto;
import com.example.demo.controller.hotel.dto.HotelResponseDto;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.hotel.entity.Hotel;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;



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
}
