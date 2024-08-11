package com.concert.reservation.interfaces.controller.token;

import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private Long tokenId;
    private String uuid;
    private Long customerId;
    private TokenStatus status;
    private LocalDateTime waitingStartDt;
    private LocalDateTime entryDt;
    private Integer rank;

    public static TokenResponse toResponse(TokenDomain tokenDomain) {
        return TokenResponse.builder()
                .tokenId(tokenDomain.getTokenId())
                .uuid(tokenDomain.getUuid())
                .customerId(tokenDomain.getCustomerId())
                .status(tokenDomain.getStatus())
                .waitingStartDt(tokenDomain.getWaitingStartDt())
                .entryDt(tokenDomain.getEntryDt())
                .rank(tokenDomain.getRank())
                .build();
    }
}

