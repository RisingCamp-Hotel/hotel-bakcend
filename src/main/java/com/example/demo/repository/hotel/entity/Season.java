package com.example.demo.repository.hotel.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isPeakSeason;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "season")
    private List<RoomPrice> roomPrices;


    public static Season create(boolean isPeakSeason, LocalDateTime startDate, LocalDateTime endDate, Hotel hotel){
        return new Season(
                null,
                isPeakSeason,
                startDate,
                endDate,
                hotel,
                new ArrayList<>()
        );
    }
}
