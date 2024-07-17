package com.concert.reservation.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
