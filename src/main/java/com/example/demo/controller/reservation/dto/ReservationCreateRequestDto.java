package com.example.demo.controller.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ReservationCreateRequestDto {
    LocalTime checkInDate;
    LocalTime checkOutDate;
    Integer hotelId;
    Integer roomTypeId;
    Integer roomNumberId;
    Integer userId;
    Integer guestCount;
}
