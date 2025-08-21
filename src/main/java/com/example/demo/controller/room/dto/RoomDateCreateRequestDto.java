package com.example.demo.controller.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDateCreateRequestDto {
    private boolean available;
    private LocalDate date;
    private Integer roomNumberId;

}
