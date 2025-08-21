package com.example.demo.repository.hotel;

import com.example.demo.repository.hotel.entity.Hotel;
import com.example.demo.repository.hotel.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {

    @Query("SELECT s FROM Season s " +
            "WHERE s.hotel = :hotel " +
            "AND :date BETWEEN s.startDate AND s.endDate")
    Optional<Season> findSeasonByDateAndHotel(@Param("date") LocalDateTime date,
                                              @Param("hotel") Hotel hotel);

}
