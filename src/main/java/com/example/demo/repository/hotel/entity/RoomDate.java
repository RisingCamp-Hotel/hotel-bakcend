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

    private boolean available;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_number_id")
    private RoomNumber roomNumber;


    public static RoomDate create(boolean available, LocalDate date, RoomNumber roomNumber) {
        return new RoomDate(
                null,
                available,
                date,
                roomNumber
        );
    }
}
