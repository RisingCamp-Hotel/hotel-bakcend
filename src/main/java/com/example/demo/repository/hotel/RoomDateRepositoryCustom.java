package com.example.demo.repository.hotel;

import com.example.demo.controller.room.dto.AvailableRoomRawDto;
import com.example.demo.controller.room.dto.AvailableRoomResponseDto;
import com.example.demo.repository.hotel.entity.RoomDate;

import java.time.LocalDate;
import java.util.List;


public interface RoomDateRepositoryCustom {
    List<AvailableRoomRawDto> findAvailableRoomByDate(LocalDate date);

    List<RoomDate> findByRoomIdAndDateBetween(Integer roomNumberId, LocalDate startDate, LocalDate endDate);

    List<AvailableRoomRawDto> findAllRoomByHotelAndDate(Integer hotelId, LocalDate date);
}
