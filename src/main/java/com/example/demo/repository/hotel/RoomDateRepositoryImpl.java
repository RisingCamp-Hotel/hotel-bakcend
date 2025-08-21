package com.example.demo.repository.hotel;

import com.example.demo.controller.room.dto.AvailableRoomRawDto;
import com.example.demo.controller.room.dto.AvailableRoomResponseDto;
import com.example.demo.repository.hotel.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomDateRepositoryImpl implements RoomDateRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AvailableRoomRawDto> findAvailableRoomByDate(LocalDate date) {
        QRoomDate rd = QRoomDate.roomDate;
        QRoomNumber rn = QRoomNumber.roomNumber;
        QRoomType rt = QRoomType.roomType;

        return queryFactory
                .select(Projections.constructor(AvailableRoomRawDto.class,
                        rt.hotel,
                        rt,
                        rd.date
                ))
                .from(rd)
                .join(rd.roomNumber, rn)
                .join(rn.roomType, rt)
                .where(
                        rd.date.eq(date)
                                .and(rd.available.isTrue())
                )
                .fetch();
    }

    @Override
    public List<RoomDate> findByRoomIdAndDateBetween(Integer roomNumberId, LocalDate startDate, LocalDate endDate) {
        QRoomDate rd = QRoomDate.roomDate;
        return queryFactory
                .selectFrom(rd)
                .where(
                        rd.roomNumber.id.eq(roomNumberId)
                                .and(rd.date.goe(startDate))
                                .and(rd.date.loe(endDate))
                )
                .fetch();
    }
}
