package com.concert.reservation.domain.token;

import com.concert.reservation.domain.token.entity.Token;
import com.concert.reservation.presentation.token.TokenRequest;
import com.concert.reservation.presentation.token.TokenResponse;

public class TokenCommand {
    public static TokenDomain toDomain (TokenRequest tokenRequest) {
        return TokenDomain.builder()
                .customerId(tokenRequest.getCustomerId())
                .build();
    }

    public static TokenDomain toDomain (Token token) {
        if (token != null) {
            return TokenDomain.builder()
                    .tokenId(token.getTokenId())
                    .customerId(token.getCustomerId())
                    .status(token.getStatus())
                    .waitingStartDt(token.getWaitingStartDt())
                    .entryDt(token.getEntryDt())
                    .build();
        }
        else {
            return null;
        }
    }

    public static Token toEntity (TokenDomain tokenDomain) {
        return Token.builder()
                .customerId(tokenDomain.getCustomerId())
                .status(tokenDomain.getStatus())
                .waitingStartDt(tokenDomain.getWaitingStartDt())
                .entryDt(tokenDomain.getEntryDt())
                .build();
    }

    public static TokenResponse toResponse (TokenDomain tokenDomain) {
        return TokenResponse.builder()
                .tokenId(tokenDomain.getTokenId())
                .customerId(tokenDomain.getCustomerId())
                .status(tokenDomain.getStatus())
                .waitingStartDt(tokenDomain.getWaitingStartDt())
                .entryDt(tokenDomain.getEntryDt())
                .build();
    }
}
