package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booking {

    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        PARTIALLY_CANCELLED,
        CANCELLED,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    private Double price;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Booking create(User user, Double price) {
        return new Booking(
            null,
                user,
                Collections.emptyList(),
                price,
                BookingStatus.PENDING,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}