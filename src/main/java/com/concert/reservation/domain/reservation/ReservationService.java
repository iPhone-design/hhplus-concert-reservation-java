package com.concert.reservation.domain.reservation;

import jakarta.transaction.Transactional;
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
     * @param   seatOptionId - 좌석 옵션 ID
     * @return  customerId - 고객 ID
     */
    public ReservationDomain findBySeatOptionIdAndCustomerId(Long seatOptionId, Long customerId) {
        return reservationRepository.findBySeatOptionIdAndCustomerId(seatOptionId, customerId);
    }

    /**
     * 예약
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationDomain - 예약 도메인
     * @return  reservationDomain
     */
    @Transactional
    public ReservationDomain save(ReservationDomain reservationDomain) {
        return reservationRepository.save(reservationDomain);
    }

    /**
     * 예약 상태 값 수정
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationId - 예약 ID
     * @param   status - 상태
     */
    @Transactional
    public void modifyStatus(Long reservationId, String status) {
        reservationRepository.modifyStatus(reservationId, status);
    }
}
