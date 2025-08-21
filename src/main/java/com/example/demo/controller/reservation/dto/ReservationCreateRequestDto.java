package com.example.demo.controller.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ReservationCreateRequestDto {
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Integer hotelId;
    Integer roomTypeId;
    Integer roomNumberId;
    Integer userId;
    Integer guestCount;
}
