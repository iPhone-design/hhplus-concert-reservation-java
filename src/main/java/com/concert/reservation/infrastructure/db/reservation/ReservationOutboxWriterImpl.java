package com.concert.reservation.infrastructure.db.reservation;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.ReservationOutboxWriter;
import com.concert.reservation.domain.reservation.entity.ReservationOutboxEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationOutboxWriterImpl implements ReservationOutboxWriter {

    private final ReservationOutboxJpaRepository reservationOutboxJpaRepository;

    /**
     * 예약 아웃 박스 조회
     *
     * @author  양종문
     * @since   2024-08-15
     * @param   status - 상태
     * @return  List<Optional<ReservationOutBoxDomain>>
     */
    @Override
    public List<ReservationOutBoxDomain> findAllByStatus(ReservationOutboxStatus status) {
        return reservationOutboxJpaRepository.findAllByStatus(status).stream().map(ReservationOutboxEvent::toDomain).collect(Collectors.toList());
    }

    /**
     * 예약 아웃 박스 상세조회
     *
     * @author  양종문
     * @since   2024-08-15
     * @param   reservationId - 예약 ID
     * @return  Optional<ReservationOutBoxDomain>
     */
    @Override
    public Optional<ReservationOutBoxDomain> findByReservationIdAndStatus(Long reservationId, ReservationOutboxStatus status) {
        return reservationOutboxJpaRepository.findByReservationIdAndStatus(reservationId, status).map(ReservationOutboxEvent::toDomain);
    }

    /**
     * 저장
     *
     * @author  양종문
     * @since   2024-08-15
     * @param   reservationOutboxDomain - 예약 아웃박스 도메인
     */
    @Override
    public void save(ReservationOutBoxDomain reservationOutboxDomain) {
        reservationOutboxJpaRepository.save(ReservationOutboxEvent.toEntity(reservationOutboxDomain));
    }
}