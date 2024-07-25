package com.concert.reservation.infrastructure.seat;

import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.seat.SeatOptionRepository;
import com.concert.reservation.domain.seat.entity.SeatOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SeatOptionRepositoryImpl implements SeatOptionRepository {

    private final SeatOptionJpaRepository seatOptionJpaRepository;

    /**
     * 좌석 상세조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   startDt - 시작일시
     * @param   endDt - 끝일시
     * @return  List<SeatOptionDomain>
     */
    @Override
    public Optional<SeatOptionDomain> findSeat(Long seatOptionId, Long concertOptionId) {
        return Optional.of(seatOptionJpaRepository.findBySeatOptionIdAndConcertOptionId(seatOptionId, concertOptionId).toDomain());
    }

    /**
     * 예약 가능 좌석 조회
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
                                                                                                          .map(SeatOption::toDomain)
                                                                                                          .collect(Collectors.toList());
    }
    
    /**
     * 좌석 저장
     *
     * @author  양종문
     * @since   2024-07-16
     * @param   seatOptionDomain - 좌석 옵션 도메인
     * @return  SeatOptionDomain
     */
    @Override
    public SeatOptionDomain save(SeatOptionDomain seatOptionDomain) {
        return seatOptionJpaRepository.saveAndFlush(SeatOption.toEntity(seatOptionDomain)).toDomain();
    }
}