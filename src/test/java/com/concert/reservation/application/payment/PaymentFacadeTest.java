package com.concert.reservation.application.payment;

import com.concert.reservation.application.customer.CustomerFacade;
import com.concert.reservation.application.reservation.ReservationFacade;
import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PaymentFacadeTest {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerFacade customerFacade;
    @Autowired
    private ReservationFacade reservationFacade;
    @Autowired
    private PaymentFacade paymentFacade;

    private static final Long customerId1 = 1L;
    private static final Long customerId2 = 1L;
    private static final Long concertOptionId = 2L;
    private static final Long seatOptionId1 = 51L;
    private static final Long seatOptionId2 = 52L;
    private static final Long seatAmount = 100000L;

    @Test
    @DisplayName("결제 잔액이 충분한 경우")
    void payment_whenAmountIsEnough() {
        // given
        customerService.save(CustomerDomain.builder().customerName("홍길동").amount(15000L).build());
        tokenService.issueToken(customerId1);
        tokenService.changeStatus(customerId1, TokenStatus.ACTIVE);
        reservationFacade.reservationConcert(customerId1, concertOptionId, seatOptionId1);

        Long amount = customerFacade.findById(customerId1).getAmount();
        Long addMount = 100000L;
        Long totalAmount = amount + addMount;
        customerFacade.chargeAmount(customerId1, addMount);

        // when
        PaymentDomain paymentDomain = paymentFacade.payment(customerId1, concertOptionId, seatOptionId1, seatAmount);
        CustomerDomain customerDomain = customerFacade.findById(customerId1);

        // then
        assertThat(paymentDomain).isNotNull();
        assertThat(customerDomain.getAmount()).isEqualTo(totalAmount - seatAmount);
    }

    @Test
    @DisplayName("결제 잔액이 충분하지 않은 경우")
    void payment_whenAmountIsNotEnough() {
        // given
        customerService.save(CustomerDomain.builder().customerName("길동무").amount(15000L).build());
        tokenService.issueToken(customerId2);
        tokenService.changeStatus(customerId2, TokenStatus.ACTIVE);
        reservationFacade.reservationConcert(customerId2, concertOptionId, seatOptionId2);

        // when-then
        assertThatThrownBy(() -> {
            paymentFacade.payment(customerId2, concertOptionId, seatOptionId2, seatAmount);
        }).isInstanceOf(CustomException.class);
    }
}