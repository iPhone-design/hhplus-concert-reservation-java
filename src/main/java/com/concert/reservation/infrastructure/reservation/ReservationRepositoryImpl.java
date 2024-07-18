package com.concert.reservation.infrastructure.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationRepository;
import com.concert.reservation.domain.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
     * 특정 고객의 미완료 상태의 예약 조회
     *
     * @author  양종문
     * @since   2024-07-18
     * @param   customerId - 고객 ID
     * @param   reservationDt - 예약일시
     * @return  List<ReservationDomain>
     */
    @Override
    public List<ReservationDomain> findAllIncompleteReservationsByCustomerIdAndReservationDt(Long customerId, LocalDateTime reservationDt) {
        return reservationJpaRepository.findAllIncompleteReservationsByCustomerIdAndReservationDt(customerId, reservationDt).stream()
                                                                                                                            .map(Reservation::toDomain)
                                                                                                                            .collect(Collectors.toList());
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
