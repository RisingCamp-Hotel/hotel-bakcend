package com.example.demo.service;


import com.example.demo.controller.room.dto.RoomCreateRequestDto;
import com.example.demo.controller.room.dto.RoomResponseDto;
import com.example.demo.repository.hotel.RoomNumberRepository;
import com.example.demo.repository.hotel.RoomTypeRepository;
import com.example.demo.repository.hotel.entity.RoomNumber;
import com.example.demo.repository.hotel.entity.RoomType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomNumberRepository roomNumberRepository;
    private final RoomTypeRepository roomTypeRepository;



    @Transactional(readOnly = true)
    public List<RoomResponseDto> findAll(){
        return roomNumberRepository.findAll()
                .stream()
                .map(RoomResponseDto::from)
                .toList();
    }

    @Transactional
    public RoomResponseDto save(RoomCreateRequestDto requestDto){
        Integer roomTypeId = requestDto.getRoomType();
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(()-> new RuntimeException("룸 타입이 존재하지 않습니다. roomTypeId = "+ roomTypeId));

        RoomNumber roomNumber = RoomNumber.create(
                requestDto.getName(),
                requestDto.getDescription(),
                roomType
        );

        RoomNumber created = roomNumberRepository.save(roomNumber);
        return RoomResponseDto.from(created);
    }


}
