package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReservationFacadeTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @Test
    @DisplayName("콘서트 좌석 예약 요청")
    void reservationConcert() {
        // given
        Long customerId = 1L;
        Long concertOptionId = 2L;
        Long seatOptionId = 51L;

        // when
        ReservationDomain reservationDomain = reservationFacade.reservationConcert(customerId, concertOptionId, seatOptionId);

        // then
        assertThat(reservationDomain).isNotNull();
    }
}