package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import com.concert.reservation.domain.token.TokenService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final TokenService tokenService;
    private final SeatOptionService seatOptionService;
    private final ReservationService reservationService;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 콘서트 좌석 예약 요청
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   seatOptionId - 좌석 옵션 ID
     * @return  reservationDomain
     */
    @Transactional
    public ReservationDomain reservationConcert(Long customerId, Long concertOptionId, Long seatOptionId)  {
        log.info("Thread : {} start", Thread.currentThread().getName());

        ReservationDomain reservationDomain = null;

        int retryCount = 0;
        while(retryCount < 10) {
            try {
                log.info("retryCount : " + retryCount);

                // 좌석 유효성 체크
                seatOptionService.checkAvailableStatus(seatOptionId, concertOptionId);

                // 좌석 상태 값 변경 (가능 → 불가능)
                seatOptionService.changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.UNAVAILABLE);

                // 예약 (미완료)
                reservationDomain = reservationService.save(customerId, concertOptionId, seatOptionId, ReservationStatus.INCOMPLETE);
                log.info("=======================================================================\n\n\n");
                log.info("[" + Thread.currentThread().getName() + "] 가 예약 완료");
                log.info("reservationDomain : {}", reservationDomain.toString());
                log.info("\n\n\n=======================================================================");
            }
            catch (Exception e) {
                log.error(String.valueOf(e));
                retryCount++;
            }
            finally {
                log.info("Thread : {} end", Thread.currentThread().getName());
            }
        }

        return reservationDomain;
    }
}
