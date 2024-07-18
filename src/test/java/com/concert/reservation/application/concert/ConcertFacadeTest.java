package com.concert.reservation.application.concert;

import com.concert.reservation.domain.concert.ConcertOptionDomain;
import com.concert.reservation.domain.concert.ConcertOptionService;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.token.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConcertFacadeTest {

    @Mock
    private ConcertOptionService concertOptionService;
    @Mock
    private SeatOptionService seatOptionService;
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private ConcertFacade concertFacade;

    @Test
    @DisplayName("예약 가능 콘서트 조회")
    void findAllAvailableConcertForReservation() {
        // given
        Long customerId = 1L;
        List<ConcertOptionDomain> listConcertOptionDomain = Arrays.asList(new ConcertOptionDomain(), new ConcertOptionDomain());

        // when
        when(concertOptionService.findAllAvailableConcertForReservation()).thenReturn(listConcertOptionDomain);

        List<ConcertOptionDomain> result = concertFacade.findAllAvailableConcertForReservation(customerId);

        // then
        assertEquals(listConcertOptionDomain.size(), result.size());
        verify(concertOptionService, times(1)).findAllAvailableConcertForReservation();
    }

    @Test
    @DisplayName("예약 가능 콘서트 좌석 조회")
    void findAllAvailableSeatForReservation() {
        // given
        Long customerId = 1L;
        LocalDate startDate = LocalDate.now();
        Long concertOptionId = 1L;
        List<SeatOptionDomain> listSeatOptionDomain = Arrays.asList(new SeatOptionDomain(), new SeatOptionDomain());

        // when
        when(seatOptionService.findAllAvailableSeatForReservation(startDate, concertOptionId)).thenReturn(listSeatOptionDomain);

        List<SeatOptionDomain> result = concertFacade.findAllAvailableSeatForReservation(customerId, startDate, concertOptionId);

        // then
        assertEquals(listSeatOptionDomain.size(), result.size());
        verify(seatOptionService, times(1)).findAllAvailableSeatForReservation(startDate, concertOptionId);
    }
}