package com.example.demo.controller.hotel.dto;

import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelSimpleResponseDto {
    private Integer id;
    private String hotelName;
    private String roomType;
    private Double minPrice;
    private boolean available;


    public static HotelSimpleResponseDto available(Hotel hotel, RoomType roomType, Double minPrice){
        return new HotelSimpleResponseDto(
                hotel.getId(),
                hotel.getHotelName(),
                roomType.getTypeName(),
                minPrice,
                true
        );
    }

    public static HotelSimpleResponseDto unavailable(Hotel hotel, RoomType roomType, Double minPrice){
        return new HotelSimpleResponseDto(
                hotel.getId(),
                hotel.getHotelName(),
                null,
                null,
                false
        );
    }


}
