package com.example.demo.repository.hotel;

import com.example.demo.repository.hotel.entity.RoomNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomNumberRepository extends JpaRepository<RoomNumber, Integer> {
}
