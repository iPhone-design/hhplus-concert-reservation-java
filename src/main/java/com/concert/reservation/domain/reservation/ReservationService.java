package com.concert.reservation.domain.reservation;

import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.interfaces.presentation.event.ReservationEvent;
import com.concert.reservation.interfaces.presentation.event.ReservationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationEventPublisher reservationEventPublisher;

    /**
     * 예약 상세조회
     *
     * @author  양종문
     * @since   2024-07-23
     * @param   reservationId - 예약 ID
     * @return  ReservationDomain
     */
    public ReservationDomain findByReservationId(Long reservationId) {
        return reservationRepository.findByReservationId(reservationId);
    }

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
     * 예약 상태 값 변경
     *
     * @author  양종문
     * @since   2024-07-23
     * @param   reservationId - 예약 Id
     * @param   status - 상태
     */
    public void changeStatus(Long reservationId, ReservationStatus status) {
        // 예약 조회
        ReservationDomain reservationDomain = this.findByReservationId(reservationId);

        // 대기
        if (ReservationStatus.INCOMPLETE.equals(status)) {
            reservationDomain.changeStatusToIncomplete();
        }
        // 활성화
        else if (ReservationStatus.COMPLETE.equals(status)) {
            reservationDomain.changeStatusToComplete();
        }

        // 저장
        reservationRepository.save(reservationDomain);
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

    /**
     * 예약 저장
     *
     * @author  양종문
     * @since   2024-07-25
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   status - 예약 상태
     * @return  ReservationDomain
     */
    public ReservationDomain save(Long customerId, Long concertOptionId, Long seatOptionId, ReservationStatus status) {
        Optional<ReservationDomain> reservationDomain = reservationRepository.findByConcertOptionIdAndSeatOptionIdAndCustomerIdNotException(concertOptionId, seatOptionId, customerId);

        if (reservationDomain.isPresent()) throw new CustomException(HttpStatus.ACCEPTED, "이미 예약이 존재합니다.");

        return reservationRepository.save(ReservationDomain.builder().customerId(customerId).concertOptionId(concertOptionId).seatOptionId(seatOptionId).status(status).build());
    }

    /**
     * 예약 성공 이벤트 발행
     *
     * @author  양종문
     * @since   2024-08-11
     */
    public void success(Long reservationId,  Long concertOptionId, Long seatOptionId, Long customerId, ReservationStatus status, LocalDateTime reservationDt) {
        reservationEventPublisher.reservationSuccessHandler(ReservationEvent.builder().reservationId(reservationId).concertOptionId(concertOptionId).seatOptionId(seatOptionId).customerId(customerId).status(status).reservationDt(reservationDt).build());
    }
}
