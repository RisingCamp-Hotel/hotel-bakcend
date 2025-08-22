package com.example.demo.repository.hotel.entity;

import com.example.demo.repository.reservation.entity.Reservation;
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
public class RoomNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id",nullable = false)
    private RoomType roomType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomNumber")
    private List<RoomDate> roomDates=  new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomNumber")
    private List<Reservation> reservations = new ArrayList<>();



    public static RoomNumber create(String name, String description, RoomType roomType){
        return new RoomNumber(
                null,
                name,
                description,
                roomType,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
