package com.concert.reservation.infrastructure.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationRepository;
import com.concert.reservation.domain.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    /**
     * 예약 상세조회
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   concertOptionId - 예약 옵션 ID
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   customerId - 고객 ID
     * @return  reservationDomain
     */
    @Override
    public ReservationDomain findByConcertOptionIdAndSeatOptionIdAndCustomerId(Long concertOptionId, Long seatOptionId, Long customerId) {
        return reservationJpaRepository.findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId).orElseThrow(() -> new IllegalArgumentException("예약 상세정보가 없습니다.")).toDomain();
    }

    /**
     * 예약 저장
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationDomain - 예약 도메인
     * @return  reservationDomain
     */
    @Override
    public ReservationDomain save(ReservationDomain reservationDomain) {
        return reservationJpaRepository.save(Reservation.toEntity(reservationDomain)).toDomain();
    }
}
