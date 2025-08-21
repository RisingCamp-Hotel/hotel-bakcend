package com.example.demo.repository.hotel;

import com.example.demo.controller.room.AvailableRoomResponseDto;
import com.example.demo.repository.hotel.entity.RoomDate;

import java.time.LocalDate;
import java.util.List;


public interface RoomDateRepositoryCustom {
    List<AvailableRoomResponseDto> findAvailableRoomByDate(LocalDate date);
}
