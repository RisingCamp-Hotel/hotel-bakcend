package com.example.demo.controller.hotel.dto;

import com.example.demo.repository.hotel.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SeasonResponseDto {
    private Integer id;
    private boolean isPeakSeason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String hotelName;

    public static SeasonResponseDto from(Season season){
        return new SeasonResponseDto(
                season.getId(),
                season.isPeakSeason(),
                season.getStartDate(),
                season.getEndDate(),
                season.getHotel().getHotelName()
                );
    }

}
