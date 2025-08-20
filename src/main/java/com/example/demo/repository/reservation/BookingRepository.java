package com.example.demo.repository.reservation;

import com.example.demo.repository.reservation.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findById(Integer id);

    List<Booking> findAll();

    Booking save(Booking entity);

    void deleteById(Integer id);
}
