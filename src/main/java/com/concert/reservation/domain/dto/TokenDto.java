package com.concert.reservation.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TokenDto {
    private Long tokenId;
    private String userUuid;
    private Integer queue;
    private String status;
    private Timestamp expireDt;
}
