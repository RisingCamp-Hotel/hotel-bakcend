package com.example.demo.repository.hotel;

import com.example.demo.controller.room.AvailableRoomResponseDto;
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
    public List<AvailableRoomResponseDto> findAvailableRoomByDate(LocalDate date) {
        QRoomDate rd = QRoomDate.roomDate;
        QRoomNumber rn = QRoomNumber.roomNumber;
        QRoomType rt = QRoomType.roomType;
        QRoomPrice rp = QRoomPrice.roomPrice;

        return queryFactory
                .select(Projections.constructor(AvailableRoomResponseDto.class,
                        rt.hotel.hotelName,
                        rt.typeName,
                        rp.price,
                        rd.date
                ))
                .from(rd)
                .join(rd.roomNumber, rn)
                .join(rn.roomType, rt)
                .join(rp).on(rp.roomType.eq(rt))
                .where(
                        rd.date.eq(date)
                                .and(rd.available.isTrue())
                )
                .orderBy(rp.price.asc())
                .fetch();
    }
}
