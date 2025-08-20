package com.example.demo.repository.hotel.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isAvailable;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_number_id")
    private RoomNumber roomNumber;


    public static RoomDate create(boolean isAvailable, LocalDate startDate, LocalDate endDate, RoomNumber roomNumber) {
        return new RoomDate(
                null,
                isAvailable,
                startDate,
                endDate,
                roomNumber
        );
    }
}
