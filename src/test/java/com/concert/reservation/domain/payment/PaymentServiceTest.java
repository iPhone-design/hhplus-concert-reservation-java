package com.concert.reservation.domain.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    @DisplayName("결제")
    void save() {
        // given
        PaymentDomain paymentDomain = PaymentDomain.builder()
                                                   .paymentId(1L)
                                                   .reservationId(1L)
                                                   .amount(50000L)
                                                   .status(PaymentStatus.COMPLETE)
                                                   .paymentDt(LocalDateTime.now())
                                                   .build();

        // when
        when(paymentService.save(any(PaymentDomain.class))).thenReturn(paymentDomain);

        PaymentDomain result =  paymentService.save(paymentDomain);

        // then
        assertThat(result).isNotNull();
        verify(paymentRepository, times(1)).save(paymentDomain);
    }
}