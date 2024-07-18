package com.concert.reservation.application.payment;

import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentService;
import com.concert.reservation.domain.payment.PaymentStatus;
import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentFacadeTest {

    @Mock
    private TokenService tokenService;
    @Mock
    private ReservationService reservationService;
    @Mock
    private CustomerService customerService;
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentFacade paymentFacade;

    @Test
    @DisplayName("결제 잔액이 충분한 경우")
    void payment() {
        // given
        Long reservationId = 4L;
        Long concertOptionId = 2L;
        Long seatOptionId = 3L;
        Long customerId = 1L;
        Long amount = 50000L;
        Long useAmount = 10000L;
        Long resultAmount = amount - useAmount;

        Long paymentId = 1L;

        ReservationDomain reservationDomain = ReservationDomain.builder()
                                                               .reservationId(reservationId)
                                                               .concertOptionId(concertOptionId)
                                                               .seatOptionId(seatOptionId)
                                                               .customerId(customerId)
                                                               .status(ReservationStatus.INCOMPLETE)
                                                               .reservationDt(LocalDateTime.now())
                                                               .build();

        PaymentDomain paymentDomain = PaymentDomain.builder()
                                                   .paymentId(paymentId)
                                                   .reservationId(reservationDomain.getReservationId())
                                                   .amount(amount)
                                                   .status(PaymentStatus.COMPLETE)
                                                   .paymentDt(LocalDateTime.now())
                                                   .build();

        CustomerDomain customerDomain = CustomerDomain.builder()
                                                      .customerId(customerId)
                                                      .customerName("테스트")
                                                      .amount(amount - useAmount)
                                                      .build();

        // when
        doNothing().when(customerService).checkAmount(customerId, amount);
        when(reservationService.findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId)).thenReturn(reservationDomain);
        when(paymentService.save(any(PaymentDomain.class))).thenReturn(paymentDomain);
        when(customerService.useAmount(customerId, amount)).thenReturn(customerDomain);
        when(reservationService.save(reservationDomain)).thenReturn(reservationDomain);
        doNothing().when(tokenService).changeStatus(customerId, TokenStatus.EXPIRE);

        PaymentDomain result = paymentFacade.payment(customerId, concertOptionId, seatOptionId, amount);

        // then
        assertEquals(paymentDomain, result);
        verify(customerService, times(1)).checkAmount(customerId, amount);
        verify(reservationService, times(1)).findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId);
        verify(paymentService, times(1)).save(any(PaymentDomain.class));
        verify(customerService, times(1)).useAmount(customerId, amount);
        verify(reservationService, times(1)).save(reservationDomain);
        verify(tokenService, times(1)).changeStatus(customerId, TokenStatus.EXPIRE);
    }
}