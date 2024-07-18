package com.concert.reservation.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * 예약 상세조회
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   concertOptionId - 예약 옵션 ID
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   customerId - 고객 ID
     * @return  ReservationDomain
     */
    public ReservationDomain findByConcertOptionIdAndSeatOptionIdAndCustomerId(Long concertOptionId, Long seatOptionId, Long customerId) {
        return reservationRepository.findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId);
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
    public List<ReservationDomain> findAllIncompleteReservationsByCustomerIdAndReservationDt(Long customerId, LocalDateTime reservationDt) {
        return reservationRepository.findAllIncompleteReservationsByCustomerIdAndReservationDt(customerId, reservationDt);
    }

    /**
     * 예약 저장
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationDomain - 예약 도메인
     * @return  ReservationDomain
     */
    public ReservationDomain save(ReservationDomain reservationDomain) {
        return reservationRepository.save(reservationDomain);
    }
}
