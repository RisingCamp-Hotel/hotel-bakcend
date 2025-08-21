package com.example.demo.init;

import com.example.demo.controller.hotel.dto.HotelCreateRequestDto;
import com.example.demo.controller.room.RoomTypeController;
import com.example.demo.controller.room.dto.RoomCreateRequestDto;
import com.example.demo.controller.room.dto.RoomDateCreateRequestDto;
import com.example.demo.controller.room.dto.RoomTypeCreateRequestDto;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.hotel.RoomPriceRepository;
import com.example.demo.repository.hotel.RoomTypeRepository;
import com.example.demo.repository.hotel.SeasonRepository;
import com.example.demo.repository.hotel.entity.RoomPrice;
import com.example.demo.repository.hotel.entity.RoomType;
import com.example.demo.repository.hotel.entity.Season;
import com.example.demo.service.HotelService;
import com.example.demo.service.RoomDateService;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final HotelService hotelService;
    private final RoomTypeService roomTypeService;
    private final RoomService roomService;
    private final RoomDateService roomDateService;
    private final RoomPriceRepository roomPriceRepository;
    private final SeasonRepository seasonRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final HotelRepository hotelRepository;


    @Override
    public void run(String... args) throws Exception{
        hotelService.save(new HotelCreateRequestDto("제주 신라호텔","서귀포시",5, "Jeju","제주최고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
        hotelService.save(new HotelCreateRequestDto("시그니엘","잠실",5, "Seoul","서울최고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
        hotelService.save(new HotelCreateRequestDto("포시즌스","동대문",5, "Seoul","서울쬐고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));

        roomTypeService.save(new RoomTypeCreateRequestDto("스탠다드",2,1));
        roomTypeService.save(new RoomTypeCreateRequestDto("디럭스",4,1));
        roomTypeService.save(new RoomTypeCreateRequestDto("스위트",8,1));

        roomService.save(new RoomCreateRequestDto("101호", "1층 방", 1));
        roomService.save(new RoomCreateRequestDto("201호", "2층 방", 2));
        roomService.save(new RoomCreateRequestDto("301호", "3층 방", 3));

        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 21),
                1 // 101호
        ));

        roomDateService.save(new RoomDateCreateRequestDto(
                false,
                LocalDate.of(2025, 8, 22),
                1
        ));

        Season summerSeason = seasonRepository.save(
                Season.create(true,
                        LocalDate.of(2025, 7, 1).atStartOfDay(),
                        LocalDate.of(2025, 8, 31).atTime(23, 59),
                        hotelRepository.findById(1).get())
        );


        RoomType standard = roomTypeRepository.findById(1).get();
        roomPriceRepository.save(RoomPrice.create(120000.0, standard, summerSeason));


        RoomType deluxe = roomTypeRepository.findById(2).get();
        roomPriceRepository.save(RoomPrice.create(200000.0, deluxe, summerSeason));


        RoomType suite = roomTypeRepository.findById(3).get();
        roomPriceRepository.save(RoomPrice.create(400000.0, suite, summerSeason));
    }





}
