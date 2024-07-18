package com.concert.reservation.application.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.customer.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerFacadeTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerFacade customerFacade;

    @Test
    @DisplayName("고객 상세조회")
    void findById() {
        // given
        Long customerId = 1L;
        CustomerDomain customerDomain = new CustomerDomain();
        customerDomain.setCustomerId(customerId);

        // when
        when(customerService.findById(customerId)).thenReturn(customerDomain);
        CustomerDomain result = customerFacade.findById(customerId);

        // then
        assertEquals(customerDomain, result);
        verify(customerService, times(1)).findById(customerId);
    }

    @Test
    @DisplayName("잔액 충전")
    void chargeAmount() {
        // given
        Long customerId = 1L;
        Long amount = 1000L;
        CustomerDomain customerDomain = new CustomerDomain();
        customerDomain.setCustomerId(customerId);
        customerDomain.setAmount(amount);

        // When
        when(customerService.chargeAmount(customerId, amount)).thenReturn(customerDomain);
        CustomerDomain result = customerFacade.chargeAmount(customerId, amount);

        // then
        assertEquals(customerDomain, result);
        verify(customerService, times(1)).chargeAmount(customerId, amount);
    }
}