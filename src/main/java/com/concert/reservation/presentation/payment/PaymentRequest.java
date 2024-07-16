package com.concert.reservation.presentation.payment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull(message = "고객 ID는 필수 항목입니다.")
    private Long customerId;
    @NotNull(message = "콘서트 옵션 ID는 필수 항목입니다.")
    private Long concertOptionId;
    @NotNull(message = "좌석 옵션 ID는 필수 항목입니다.")
    private Long seatOptionId;
    @NotNull(message = "결제 금액은 필수 항목입니다.")
    private Long amount;
}
