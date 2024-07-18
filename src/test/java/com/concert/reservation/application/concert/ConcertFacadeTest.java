package com.concert.reservation.application.concert;

import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.concert.ConcertOptionDomain;
import com.concert.reservation.domain.concert.ConcertOptionService;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConcertFacadeTest {

    @Mock
    private ConcertOptionService concertOptionService;
    @Mock
    private SeatOptionService seatOptionService;

    @InjectMocks
    private ConcertFacade concertFacade;

    private Long concertOptionId;
    private LocalDate startDate;
    private ConcertOptionDomain concert1;
    private ConcertOptionDomain concert2;
    private List<ConcertOptionDomain> listConcertOption1;
    private List<ConcertOptionDomain> listConcertOption2;
    private SeatOptionDomain seat1;
    private SeatOptionDomain seat2;
    private List<SeatOptionDomain> listSeatOptionDomain;

    @BeforeEach
    void setUp() {
        concert1 =  ConcertOptionDomain.builder().concertId(1L).concertName("잠실 공연").location("잠실").openDt(LocalDate.now().minusDays(2).atStartOfDay()).build();
        concert2 =  ConcertOptionDomain.builder().concertId(1L).concertName("신촌 공연").location("신촌").openDt(LocalDate.now().plusDays(5).atStartOfDay()).build();

        listConcertOption1 = new ArrayList<>();
        listConcertOption1.add(concert1);
        listConcertOption1.add(concert2);

        listConcertOption2 = new ArrayList<>();
        listConcertOption2.add(concert2);

        startDate = LocalDate.now();
        concertOptionId = 2L;

        seat1 = new SeatOptionDomain(1L, 1L, 1L,10000L, "AVAILABLE");
        seat2 = new SeatOptionDomain(2L, 2L, 1L,30000L, "UNAVAILABLE");

        listSeatOptionDomain = new ArrayList<>();
        listSeatOptionDomain.add(seat1);
        listSeatOptionDomain.add(seat2);
    }

    @Test
    @Description("예약 가능 콘서트 조회")
    void testFindAllAvailableConcertForReservation() {
        // when
        when(concertOptionService.findAllAvailableConcertForReservation()).thenReturn(listConcertOption1);
        List<ConcertOptionDomain> result = concertFacade.findAllAvailableConcertForReservation();

        // then
        assertEquals(2, result.size());
        assertEquals("잠실 공연", result.get(0).getConcertName());
        assertEquals("신촌 공연", result.get(1).getConcertName());

        // verify
        verify(concertOptionService, times(1)).findAllAvailableConcertForReservation();
    }

    @Test
    @Description("예약 가능 콘서트 좌석 조회")
    void testFindAllAvailableSeatForReservation() {
        // given
        LocalDate startDate = LocalDate.now().plusDays(1);
        Long concertOptionId = 1L;

        // when
        when(concertFacade.findAllAvailableSeatForReservation(startDate, concertOptionId)).thenReturn(listSeatOptionDomain);
        List<SeatOptionDomain> result = concertFacade.findAllAvailableSeatForReservation(startDate, concertOptionId);

        // then
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getSeatId());

        // verify
        verify(seatOptionService, times(1)).findAllAvailableSeatForReservation(startDate, concertOptionId);
    }
}