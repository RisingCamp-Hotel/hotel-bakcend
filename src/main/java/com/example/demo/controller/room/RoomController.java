package com.example.demo.controller.room;

import com.example.demo.controller.room.dto.RoomResponseDto;
import com.example.demo.service.RoomDateService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomDateService roomDateService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> findAll(){
        List<RoomResponseDto> rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableRoomResponseDto>> getAvailableRooms(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(roomDateService.findAvailableByDate(date));
    }
}
