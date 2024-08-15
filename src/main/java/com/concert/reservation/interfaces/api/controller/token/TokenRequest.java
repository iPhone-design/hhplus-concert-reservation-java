package com.concert.reservation.interfaces.api.controller.token;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    @NotNull(message = "고객 ID는 필수 항목입니다.")
    private Long customerId;
}
