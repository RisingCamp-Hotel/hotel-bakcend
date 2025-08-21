package com.example.demo.controller.hotel.dto;

import com.example.demo.repository.hotel.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HotelDetailResponseDto {
    private HotelResponseDto hotel;
    private List<RoomAvailabilityDto> rooms;

    public static HotelDetailResponseDto from(Hotel hotel, List<RoomAvailabilityDto> rooms){
        return new HotelDetailResponseDto(
                HotelResponseDto.from(hotel),
                rooms
        );
    }
}
