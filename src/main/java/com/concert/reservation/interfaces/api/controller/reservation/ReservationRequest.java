package com.concert.reservation.interfaces.api.controller.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    @NotNull(message = "콘서트 옵션 ID는 필수 항목입니다.")
    private Long concertOptionId;
    @NotNull(message = "좌석 옵션 ID는 필수 항목입니다.")
    private Long seatOptionId;
    @NotNull(message = "고객 ID는 필수 항목입니다.")
    private Long customerId;
}

