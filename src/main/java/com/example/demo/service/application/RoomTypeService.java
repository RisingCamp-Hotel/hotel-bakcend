package com.example.demo.service.application;

import com.example.demo.controller.room.dto.RoomTypeCreateRequestDto;
import com.example.demo.controller.room.dto.RoomTypeResponseDto;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.hotel.RoomTypeRepository;
import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final HotelRepository hotelRepository;



    @Transactional(readOnly = true)
    public RoomTypeResponseDto findById(Integer id){

        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 방을 찾을 수 없습니다. 방 Id = "+ id));

        return RoomTypeResponseDto.from(roomType);

    }

    @Transactional(readOnly = true)
    public List<RoomTypeResponseDto> findAll(){
        return roomTypeRepository.findAll()
                .stream()
                .map(RoomTypeResponseDto::from)
                .toList();
    }

    @Transactional
    public RoomTypeResponseDto save(RoomTypeCreateRequestDto requestDto){

        Integer hotelId = requestDto.getHotelId();
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new RuntimeException("호텔이 디비에 존재하지 않습니다. 호텔 id ="+ hotelId));

        RoomType roomType = RoomType.create(
                requestDto.getTypeName(),
                requestDto.getCapacity(),
                hotel,
                requestDto.getBasePrice()
        );

        RoomType created = roomTypeRepository.save(roomType);
        return RoomTypeResponseDto.from(created);
    }
}
