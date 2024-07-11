package com.concert.reservation.presentation.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private Long tokenId;
    private Long customerId;
    private String status;
    private Timestamp waitingStartDt;
    private Timestamp entryDt;
}

