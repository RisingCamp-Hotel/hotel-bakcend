package com.example.demo.service.reservation;

import com.example.demo.controller.reservation.dto.ReservationCreateRequestDto;
import com.example.demo.controller.reservation.dto.ReservationResponseDto;
import com.example.demo.repository.reservation.ReservationRepository;
import com.example.demo.repository.reservation.entity.Reservation;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReservationResponseDto save(ReservationCreateRequestDto request) {
        // 날짜, 호텔, 객실타입, 호수, 유저아이디
    }

    @Transactional
    public void delete(Integer reservationId, Integer userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("not exists reservation"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("not exists user"));
        reservationRepository.deleteById(reservationId);
    }
}
