package com.example.demo.repository.user.entity;

import com.example.demo.repository.reservation.entity.Booking;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static User create(String username, String password, String email) {
        return new User(
                null,
                Collections.emptyList(),
                username,
                password,
                email,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}
