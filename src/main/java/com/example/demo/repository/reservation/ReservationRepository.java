package com.example.demo.repository.reservation;

import com.example.demo.repository.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(Integer id);

    List<Reservation> findAll();

    Reservation save(Reservation entity);

    void deleteById(Integer id);
}
