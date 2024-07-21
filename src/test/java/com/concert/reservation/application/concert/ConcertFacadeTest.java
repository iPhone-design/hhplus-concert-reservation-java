package com.concert.reservation.application.concert;

import com.concert.reservation.domain.concert.ConcertOptionDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConcertFacadeTest {

    @Autowired
    ConcertFacade concertFacade;

    @Test
    @DisplayName("예약 가능 콘서트 조회")
    void findAllAvailableConcertForReservation() {
        // given-when
        List<ConcertOptionDomain> listConcertOptionDomain = concertFacade.findAllAvailableConcertForReservation();

        // then
        assertThat(listConcertOptionDomain).isNotNull();
    }

    @Test
    @DisplayName("예약 가능 콘서트 좌석 조회")
    void findAllAvailableSeatForReservation() {
        // given-when
        List<ConcertOptionDomain> listConcertOptionDomain = concertFacade.findAllAvailableConcertForReservation();

        // then
        assertThat(listConcertOptionDomain).isNotNull();
    }
}