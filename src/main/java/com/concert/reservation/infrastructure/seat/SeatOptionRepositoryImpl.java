package com.concert.reservation.infrastructure.seat;

import com.concert.reservation.application.seat.SeatOptionRepository;
import com.concert.reservation.domain.seat.SeatOptionCommand;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SeatOptionRepositoryImpl implements SeatOptionRepository {

    private final SeatOptionJpaRepository seatOptionJpaRepository;

    /**
     * 예약 가능 콘서트 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   startDt - 시작일시
     * @param   endDt - 끝일시
     * @return  List<SeatOptionDomain>
     */
    @Override
    public List<SeatOptionDomain> findAllAvailableSeatForReservation(Long concertOptionId, LocalDateTime startDt, LocalDateTime endDt) {
        return seatOptionJpaRepository.findAllAvailableSeatForReservation(concertOptionId, startDt, endDt).stream()
                                                                                                          .map(SeatOptionCommand::toDomain)
                                                                                                          .collect(Collectors.toList());
    }
}