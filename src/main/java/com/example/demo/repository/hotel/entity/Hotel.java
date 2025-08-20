package com.example.demo.repository.hotel.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hotelName;
    private String address;
    private Integer hotelRating;
    private String city;
    private String description;

    private Double rate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Season> seasons = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<RoomType> roomTypes = new ArrayList<>();

    public static Hotel create(String hotelName, String address, Integer hotelRating,
                               String city, String description, Double rate, LocalTime checkInTime, LocalTime checkOutTime) {
        return new Hotel(
                null, hotelName, address, hotelRating, city, description,
                rate,
                LocalDateTime.now(),
                LocalDateTime.now(),
                checkInTime,
                checkOutTime,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}


