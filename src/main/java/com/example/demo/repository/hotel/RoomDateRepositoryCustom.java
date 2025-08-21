package com.example.demo.repository.hotel;

import com.example.demo.controller.room.dto.AvailableRoomRawDto;
import com.example.demo.controller.room.dto.AvailableRoomResponseDto;

import java.time.LocalDate;
import java.util.List;


public interface RoomDateRepositoryCustom {
    List<AvailableRoomRawDto> findAvailableRoomByDate(LocalDate date);
}
