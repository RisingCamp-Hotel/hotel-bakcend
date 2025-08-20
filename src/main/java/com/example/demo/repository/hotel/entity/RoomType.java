package com.example.demo.repository.hotel.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String typeName;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomType")
    private List<RoomPrice> roomPrices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomType")
    private List<RoomNumber> roomNumbers;


    public static RoomType create(String typeName, Integer capacity, Hotel hotel){
        return new RoomType(
                null,
                typeName,
                capacity,
                hotel,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }


}
