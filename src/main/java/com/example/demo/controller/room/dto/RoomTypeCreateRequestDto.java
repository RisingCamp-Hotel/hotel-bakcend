package com.example.demo.controller.room.dto;

import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeCreateRequestDto {
    private String typeName;
    private Integer capacity;
    private Integer hotelId;

    public RoomType from(Hotel hotel) {
        return RoomType.create(
                typeName,
                capacity,
                hotel
        );
    }
}
