package com.concert.reservation.infrastructure.reservation;

import com.concert.reservation.application.reservation.ReservationRepository;
import com.concert.reservation.domain.reservation.ReservationCommand;
import com.concert.reservation.domain.reservation.ReservationDomain;
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
     * @param   reservationId - 예약 ID
     * @return  reservationDomain
     */
    @Override
    public ReservationDomain findBySeatOptionIdAndCustomerId(Long seatOptionId, Long customerId) {
        return ReservationCommand.toDomain(reservationJpaRepository.findBySeatOptionIdAndCustomerId(seatOptionId, customerId).orElseThrow(() -> new IllegalArgumentException("예약 상세정보가 없습니다.")));
    }

    /**
     * 예약
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationDomain - 예약 도메인
     * @return  reservationDomain
     */
    @Override
    public ReservationDomain save(ReservationDomain reservationDomain) {
        return ReservationCommand.toDomain(reservationJpaRepository.save(ReservationCommand.toEntity(reservationDomain)));
    }

    /**
     * 예약 상태 값 수정
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationId - 예약 ID
     * @param   status - 상태
     */
    @Override
    public void modifyStatus(Long reservationId, String status) {
        reservationJpaRepository.updateStatus(reservationId, status);
    }
}
