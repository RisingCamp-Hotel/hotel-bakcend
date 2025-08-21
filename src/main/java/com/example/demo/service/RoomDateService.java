package com.example.demo.service;

import com.example.demo.controller.room.AvailableRoomResponseDto;
import com.example.demo.controller.room.dto.RoomDateCreateRequestDto;
import com.example.demo.controller.room.dto.RoomDateResponseDto;
import com.example.demo.repository.hotel.RoomDateRepository;
import com.example.demo.repository.hotel.RoomNumberRepository;
import com.example.demo.repository.hotel.entity.RoomDate;
import com.example.demo.repository.hotel.entity.RoomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomDateService {
    private final RoomDateRepository roomDateRepository;
    private final RoomNumberRepository roomNumberRepository;

    @Transactional(readOnly = true)
    public List<RoomDateResponseDto> findAll() {
        return roomDateRepository.findAll().stream()
                .map(RoomDateResponseDto::from)
                .toList();
    }

    @Transactional
    public RoomDateResponseDto save(RoomDateCreateRequestDto requestDto) {
        RoomNumber roomNumber = roomNumberRepository.findById(requestDto.getRoomNumberId())
                .orElseThrow(() -> new RuntimeException("해당 방이 존재하지 않습니다. id=" + requestDto.getRoomNumberId()));

        RoomDate roomDate = RoomDate.create(
                requestDto.isAvailable(),
                requestDto.getDate(),
                roomNumber
        );

        return RoomDateResponseDto.from(roomDateRepository.save(roomDate));
    }

    @Transactional(readOnly = true)
    public List<AvailableRoomResponseDto> findAvailableByDate(LocalDate date) {
        return roomDateRepository.findAvailableRoomByDate(date);
    }
}
