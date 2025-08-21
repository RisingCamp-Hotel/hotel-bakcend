package com.example.demo.controller.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AvailableRoomResponseDto {
    private String hotelName;
    private String roomTypeName;
    private Double price;
    private LocalDate date;

}
