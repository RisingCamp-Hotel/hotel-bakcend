package com.example.demo.repository.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    public enum ReservationStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    // 예약묶음
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    // 방 호수 Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private RoomNumber roomNumber;

    // localtime?
//    private LocalDateTime checkInDate;
//    private LocalDateTime checkOutDate;
    private LocalTime checkInDate;
    private LocalTime checkOutDate;

    private Integer guestCount;
    private Boolean isWalkIn;
    private LocalDateTime arrival;
    private String requirements;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private Double priceSnapshot;

    @Builder
    public static Reservation create(
            RoomNumber roomNumber,
            LocalTime checkInDate,
            LocalTime checkOutDate,
            Integer guestCount,
            Boolean isWalkIn,
            LocalDateTime arrival,
            Double priceSnapshot
    ) {
        return new Reservation(
                null,
                null,
                roomNumber,
                checkInDate,
                checkOutDate,
                guestCount,
                isWalkIn,
                arrival,
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                ReservationStatus.PENDING,
                priceSnapshot
        );
    }
}
