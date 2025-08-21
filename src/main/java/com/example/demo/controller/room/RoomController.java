package com.example.demo.controller.room;

import com.example.demo.controller.room.dto.RoomResponseDto;
import com.example.demo.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> findAll(){
        List<RoomResponseDto> rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }
}
