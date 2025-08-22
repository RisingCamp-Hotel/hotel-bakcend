package com.example.demo.controller.room;

import com.example.demo.controller.room.dto.RoomTypeResponseDto;
import com.example.demo.service.application.RoomTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomtypes")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class RoomTypeController {

    RoomTypeService roomTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeResponseDto> findById(@PathVariable Integer id){
        RoomTypeResponseDto roomType = roomTypeService.findById(id);
        return ResponseEntity.ok(roomType);
    }

    @GetMapping
    public ResponseEntity<List<RoomTypeResponseDto>> findAll(){
        List<RoomTypeResponseDto> roomTypes = roomTypeService.findAll();
        return ResponseEntity.ok(roomTypes);
    }
}
