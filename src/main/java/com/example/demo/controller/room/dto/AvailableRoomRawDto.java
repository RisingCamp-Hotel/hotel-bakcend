package com.example.demo.controller.room.dto;

import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AvailableRoomRawDto {
    private Hotel hotel;
    private RoomType roomType;
    private LocalDate localDate;
    private Boolean available;
}
