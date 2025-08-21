package com.example.demo.controller.hotel.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;


@Getter
@AllArgsConstructor
public class HotelCreateRequestDto {
    private String hotelName;
    private String address;
    private Integer hotelRating;
    private String city;
    private String description;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
}
