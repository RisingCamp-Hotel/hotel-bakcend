package com.example.demo.service.domain;

import com.example.demo.repository.hotel.SeasonRepository;
import com.example.demo.repository.hotel.entity.RoomType;
import com.example.demo.repository.hotel.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PricingService {
    private final SeasonRepository seasonRepository;

    public double calculate(LocalDate date, RoomType roomType) {
        return seasonRepository.findSeasonByDateAndHotel(date.atStartOfDay(), roomType.getHotel())
                .filter(Season::isPeakSeason)
                .map(s -> roomType.getBasePrice() * 2)
                .orElse(roomType.getBasePrice());
    }
}
