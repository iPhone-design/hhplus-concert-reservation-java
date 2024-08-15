package com.concert.reservation.interfaces.api.controller.payment;

import com.concert.reservation.application.payment.PaymentFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    /**
     * 결제 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   paymentRequest - 고객 요청
     * @return  paymentRequest
     */
    @PostMapping("/pay")
    public PaymentResponse payment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return PaymentResponse.toResponse(paymentFacade.payment(paymentRequest.getCustomerId(), paymentRequest.getConcertOptionId(), paymentRequest.getSeatOptionId(), paymentRequest.getAmount()));
    }
}
