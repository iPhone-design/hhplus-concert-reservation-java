package com.concert.reservation.application.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerFacadeTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerFacade customerFacade;

    @BeforeEach
    void setup() {
        // given
        customerService.deleteAll();

        String customerName = "홍길동";
        Long amount = 10000L;
        customerService.save(CustomerDomain.builder().customerName(customerName).amount(amount).build());
    }

    @Test
    @DisplayName("고객 상세조회")
    void findById() {
        Long customerId = 1L;

        // when
        CustomerDomain customerDomain = customerFacade.findById(customerId);

        // then
        assertThat(customerId).isEqualTo(customerDomain.getCustomerId());
    }

    @Test
    @DisplayName("잔액 충전")
    void chargeAmount() {
        Long customerId = 2L;
        Long amount = 10000L;
        Long addAmount = 5000L;

        // when
        CustomerDomain customerDomain = customerService.chargeAmount(customerId, addAmount);

        // then
        assertThat(amount + addAmount).isEqualTo(customerDomain.getAmount());
    }
}