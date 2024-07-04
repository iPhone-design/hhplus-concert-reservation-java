package com.concert.reservation.presentation.contoller;

import com.concert.reservation.domain.dto.PaymentDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    /**
     * 결제 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   paymentDto
     * @return  PaymentDto
     */
    @PostMapping("/pay")
    public PaymentDto paymentPay(PaymentDto paymentDto) {
        return new PaymentDto();
    }
}
