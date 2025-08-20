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

    @PostConstruct
    public void init(){
        this.save(new HotelCreateRequestDto("제주 신라호텔","서귀포시",5, "Jeju","제주최고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
        this.save(new HotelCreateRequestDto("시그니엘","잠실",5, "Seoul","서울최고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
        this.save(new HotelCreateRequestDto("포시즌스","동대문",5, "Seoul","서울쬐고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
    }



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
