package com.example.demo.repository.hotel;

import com.example.demo.repository.hotel.entity.RoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Integer> {
}
