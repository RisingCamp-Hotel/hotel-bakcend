package com.example.demo.controller.hotel;

import com.example.demo.controller.hotel.dto.HotelDetailResponseDto;
import com.example.demo.controller.hotel.dto.HotelResponseDto;
import com.example.demo.service.application.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelController {

    HotelService hotelService;

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDto> hotel(@PathVariable Integer id){
        HotelResponseDto hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<HotelDetailResponseDto> hotelDetail(
            @PathVariable Integer id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        HotelDetailResponseDto detail = hotelService.getHotelDetail(id, date);
        return ResponseEntity.ok(detail);
    }
}
