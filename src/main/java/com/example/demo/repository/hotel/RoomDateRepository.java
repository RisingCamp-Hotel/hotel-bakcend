package com.example.demo.repository.hotel;

import com.example.demo.repository.hotel.entity.RoomDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDateRepository extends JpaRepository<RoomDate, Integer>, RoomDateRepositoryCustom {
}
