package com.example.demo.init;

import com.example.demo.controller.hotel.dto.HotelCreateRequestDto;
import com.example.demo.controller.hotel.dto.SeasonCreateRequestDto;
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
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import com.example.demo.service.*;
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
    private final SeasonService seasonService;
    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception{
        User user = User.create("testuser", "testuser@email.com", "password123");
        userRepository.save(user);

        hotelService.save(new HotelCreateRequestDto("제주 신라호텔","서귀포시",5, "Jeju","제주최고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
        hotelService.save(new HotelCreateRequestDto("시그니엘","잠실",5, "Seoul","서울최고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));
        hotelService.save(new HotelCreateRequestDto("포시즌스","동대문",5, "Seoul","서울쬐고호텔", LocalTime.of(14,0), LocalTime.of(11,0)));

        roomTypeService.save(new RoomTypeCreateRequestDto("스탠다드",2,1,200000.0));
        roomTypeService.save(new RoomTypeCreateRequestDto("디럭스",4,1,400000.0));
        roomTypeService.save(new RoomTypeCreateRequestDto("스위트",8,1,800000.0));

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


        roomDateService.save(new RoomDateCreateRequestDto(
                false,
                LocalDate.of(2025, 8, 19),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                false,
                LocalDate.of(2025, 8, 20),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 21),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 22),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 23),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 24),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 25),
                2
        ));
        roomDateService.save(new RoomDateCreateRequestDto(
                true,
                LocalDate.of(2025, 8, 26),
                2
        ));

        seasonService.save(new SeasonCreateRequestDto(
                true,
                LocalDate.of(2025, 7, 1).atStartOfDay(),
                LocalDate.of(2025, 8, 31).atTime(23, 59),
                1 // 호텔 id
        ));


    }





}
