package com.example.demo.controller.hotel;

import com.example.demo.controller.hotel.dto.SeasonCreateRequestDto;
import com.example.demo.controller.hotel.dto.SeasonResponseDto;
import com.example.demo.service.application.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seasons")
public class SeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public ResponseEntity<SeasonResponseDto> createSeason(@RequestBody SeasonCreateRequestDto requestDto){
        return ResponseEntity.ok(seasonService.save(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<SeasonResponseDto>> getSeasons() {
        return ResponseEntity.ok(seasonService.findAll());
    }
}
