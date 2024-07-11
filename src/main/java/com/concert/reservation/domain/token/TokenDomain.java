package com.concert.reservation.domain.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDomain {
    private Long tokenId;
    private Long customerId;
    private String status;
    private Timestamp waitingStartDt;
    private Timestamp entryDt;
}
