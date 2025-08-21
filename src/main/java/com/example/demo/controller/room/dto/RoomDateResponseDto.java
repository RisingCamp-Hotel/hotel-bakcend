package com.example.demo.controller.room.dto;

import com.example.demo.repository.hotel.entity.RoomDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoomDateResponseDto {
    private Integer id;
    private boolean available;
    private LocalDate date;
    private Integer roomNumberId;

    public static RoomDateResponseDto from(RoomDate entity){
        return new RoomDateResponseDto(
                entity.getId(),
                entity.isAvailable(),
                entity.getDate(),
                entity.getRoomNumber().getId()
        );
    }
}
