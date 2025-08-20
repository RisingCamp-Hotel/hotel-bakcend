package com.example.demo.controller.reservation;

import com.example.demo.controller.reservation.dto.ReservationResponseDto;
import com.example.demo.service.reservation.BookingService;
import com.example.demo.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    public ResponseEntity<ReservationResponseDto> createBooking(@RequestBody ReservationResponseDto request) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer reservationId, @PathVariable Integer userId) {
        reservationService.delete(reservationId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
