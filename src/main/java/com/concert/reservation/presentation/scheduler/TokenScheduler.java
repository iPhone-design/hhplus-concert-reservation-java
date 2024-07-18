package com.concert.reservation.presentation.scheduler;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TokenScheduler {

    private final TokenService tokenService;
    private final ReservationService reservationService;
    private final SeatOptionService seatOptionService;

    /**
     * 토큰 만료와 좌석 활성화 시키는 스케줄러
     *
     * @author  양종문
     * @since   2024-07-18
     */
    @Scheduled(cron = "0 */4 * * * *") // 4분마다 실행
    @Transactional
    public void tokensChangeStatusToExpireAndSeatOptionsChangeStatusToAvailable() {
        // 토큰 조회 (활성화가 된 지 4분이 지난 대상)
        List<TokenDomain> listTokenDomain = tokenService.findAllActiveTokensOlderThanFourMinutes();
        for (TokenDomain tokenDomain : listTokenDomain) {
            Long customerId = tokenDomain.getCustomerId();
            LocalDateTime entryDt = tokenDomain.getEntryDt();

            // 토큰 상태 값 변경 (활성 → 만료)
            tokenService.changeStatus(customerId, TokenStatus.EXPIRE);

            // 특정 고객의 미완료 상태의 예약 조회 (토큰 활성화 시점 이후에 예약된 대상으로 한정)
            List<ReservationDomain> listReservationDomain = reservationService.findAllIncompleteReservationsByCustomerIdAndReservationDt(customerId, entryDt);
            for (ReservationDomain reservationDomain : listReservationDomain) {
                Long seatOptionId = reservationDomain.getSeatOptionId();
                Long concertOptionId = reservationDomain.getConcertOptionId();

                // 좌석 상태 값 변경 (불가능 → 가능)
                seatOptionService.changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.AVAILABLE);
            }
        }
    }
}
