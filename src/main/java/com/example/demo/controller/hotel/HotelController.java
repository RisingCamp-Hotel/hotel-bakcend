package com.example.demo.controller.hotel;

import com.example.demo.controller.hotel.dto.HotelResponseDto;
import com.example.demo.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
