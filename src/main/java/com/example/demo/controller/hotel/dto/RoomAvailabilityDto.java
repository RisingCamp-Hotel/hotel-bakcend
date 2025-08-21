package com.example.demo.controller.hotel.dto;

import com.example.demo.repository.hotel.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomAvailabilityDto {
    private Integer id;
    private String typeName;
    private Integer capacity;
    private boolean available;
    private Double price;
    private String message;

    public static  RoomAvailabilityDto from(RoomType roomType, boolean available, Double price){
        return new RoomAvailabilityDto(
                roomType.getId(),
                roomType.getTypeName(),
                roomType.getCapacity(),
                available,
                price,
                available ? "예약 가능!" : "예약 불가능~~"
        );
    }
}
