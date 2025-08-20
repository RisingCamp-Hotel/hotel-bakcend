package com.example.demo.controller.hotel.dto;

import com.example.demo.repository.hotel.entity.Hotel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelResponseDto {
    private Integer id;
    private String hotelName;
    private String address;
    private Integer hotelRating;
    private String city;
    private String description;
    private Double rate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    public static HotelResponseDto from(Hotel hotel){
        return new HotelResponseDto(
                hotel.getId(),
                hotel.getHotelName(),
                hotel.getAddress(),
                hotel.getHotelRating(),
                hotel.getCity(),
                hotel.getDescription(),
                hotel.getRate(),
                hotel.getCheckInTime(),
                hotel.getCheckOutTime()
        );
    }
}
