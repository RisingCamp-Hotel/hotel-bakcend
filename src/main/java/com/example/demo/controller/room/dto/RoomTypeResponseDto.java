package com.example.demo.controller.room.dto;

import com.example.demo.repository.hotel.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomTypeResponseDto {
    private Integer id;
    private String typeName;
    private Integer capacity;
    private Integer hotelId;

    public static RoomTypeResponseDto from(RoomType entity) {
        return new RoomTypeResponseDto(
                entity.getId(),
                entity.getTypeName(),
                entity.getCapacity(),
                entity.getHotel().getId()
        );
    }
}
