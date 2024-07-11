package com.concert.reservation.presentation.payment;

import com.concert.reservation.application.payment.PaymentFacade;
import com.concert.reservation.domain.payment.PaymentCommand;
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
        return PaymentCommand.toResponse(paymentFacade.payment(paymentRequest.getSeatOptionId(), paymentRequest.getCustomerId(), paymentRequest.getConcertOptionId(), paymentRequest.getDate()));
    }
}
