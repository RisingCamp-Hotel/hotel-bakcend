package com.example.demo.controller.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SeasonCreateRequestDto {
    private boolean isPeakSeason;   // 성수기 여부
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer hotelId;
}
