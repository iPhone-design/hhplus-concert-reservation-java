package com.concert.reservation.presentation.customer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank(message = "고객 ID는 필수 항목입니다.")
    private Long customerId;
    @Min(value = 0, message = "최소 금액은 0원 이상입니다.")
    private Long amount;
}
