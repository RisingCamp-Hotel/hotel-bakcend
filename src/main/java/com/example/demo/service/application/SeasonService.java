package com.example.demo.service.application;

import com.example.demo.controller.hotel.dto.SeasonCreateRequestDto;
import com.example.demo.controller.hotel.dto.SeasonResponseDto;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.hotel.SeasonRepository;
import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final HotelRepository hotelRepository;

    @Transactional
    public SeasonResponseDto save(SeasonCreateRequestDto requestDto){
        Hotel hotel = hotelRepository.findById(requestDto.getHotelId())
                .orElseThrow(()-> new RuntimeException("호텔이 존재하지 않습니다. 호텔 id = "+ requestDto.getHotelId()));

        Season season = Season.create(
                requestDto.isPeakSeason(),
                requestDto.getStartDate(),
                requestDto.getEndDate(),
                hotel
        );

        Season created = seasonRepository.save(season);

        return SeasonResponseDto.from(created);
    }

    @Transactional(readOnly = true)
    public List<SeasonResponseDto> findAll(){
        return seasonRepository.findAll().stream()
                .map(SeasonResponseDto::from)
                .toList();
    }


}
