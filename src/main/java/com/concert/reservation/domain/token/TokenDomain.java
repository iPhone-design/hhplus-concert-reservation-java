package com.concert.reservation.domain.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDomain {
    private Long tokenId;
    private Long customerId;
    private TokenStatus status;
    private LocalDateTime waitingStartDt;
    private LocalDateTime entryDt;

    public void changeStatusToWaiting() {
        this.status = TokenStatus.WAITING;
        this.waitingStartDt = LocalDateTime.now();
        this.entryDt = null;
    }

    public void changeStatusToActive() {
        this.status = TokenStatus.ACTIVE;
        this.entryDt = LocalDateTime.now();
    }

    public void changeStatusToExpire() {
        this.status = TokenStatus.EXPIRE;
        this.waitingStartDt = null;
        this.entryDt = null;
    }

    public static TokenDomain createToken(Long customerId) {
        return TokenDomain.builder()
                .customerId(customerId)
                .status(TokenStatus.WAITING)
                .waitingStartDt(LocalDateTime.now())
                .build();
    }
}
