package com.example.demo.controller.room.dto;

import com.example.demo.repository.hotel.entity.RoomNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RoomResponseDto {
    private Integer id;
    private String name;
    private Integer roomTypeId;
    private String roomTypeName;

    public static RoomResponseDto from(RoomNumber entity){
        return new RoomResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getRoomType().getId(),
                entity.getRoomType().getTypeName()
        );
    }
}
