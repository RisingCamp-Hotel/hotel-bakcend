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

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;



    @Transactional
    public void delete(Integer reservationId, Integer userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("not exists reservation"));

        // user 객체 자체는 필요 없는 거 같고 단순히 존재하는 userId인지 확인용으로라도 남겨야하나?
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("not exists user"));

        // 예약자 본인 확인
            // reservatui -> booking -> user로 검사?
        if (!reservation.getBooking().getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("예약자 본인이 아닙니다");
        }

        // 날짜 당일 or 지난 예약 확인
        if (!reservation.getCheckInDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("당일 혹은 지난 예약을 취소할 수 없습니다.");
        }
        reservationRepository.deleteById(reservationId);
    }
}
