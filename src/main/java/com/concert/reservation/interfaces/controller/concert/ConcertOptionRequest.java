package com.concert.reservation.interfaces.controller.concert;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOptionRequest {
    @NotNull(message = "고객 ID는 필수 값입니다.")
    private Long customerId;
}

