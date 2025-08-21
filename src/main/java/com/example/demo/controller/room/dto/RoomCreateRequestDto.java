package com.example.demo.controller.room.dto;

import com.example.demo.repository.hotel.entity.RoomNumber;
import com.example.demo.repository.hotel.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateRequestDto {
    private String name;
    private String description;
    private Integer roomType;

    public RoomNumber from(RoomType roomType){
        return RoomNumber.create(
                name,
                description,
                roomType
        );
    }
}
