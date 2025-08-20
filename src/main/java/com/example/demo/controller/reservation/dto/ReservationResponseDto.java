package com.example.demo.controller.reservation.dto;

import com.example.demo.repository.hotel.entity.RoomNumber;
import com.example.demo.repository.reservation.entity.Booking;
import com.example.demo.repository.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ReservationResponseDto {
    private Integer id;

    // 다른 테이블 필드
    private String hotelName;
    private String roomTypeName;
    private String roomNumberName;

//    private RoomNumber roomNumber;
    private LocalTime checkInDate;
    private LocalTime checkOutDate;
    private Integer guestCount;
    private String status;
    private Double priceSnapshot;

    public static ReservationResponseDto from(Reservation entity) {
        return new ReservationResponseDto(
                entity.getId(),

                entity.getRoomNumber().getRoomType().getHotel().getHotelName(),
                entity.getRoomNumber().getRoomType().getTypeName(),
                entity.getRoomNumber().getName(),

                entity.getCheckInDate(),
                entity.getCheckOutDate(),
                entity.getGuestCount(),
                entity.getStatus().name(),
                entity.getPriceSnapshot()
        );
    }
}
