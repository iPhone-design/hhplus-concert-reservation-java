package com.concert.reservation.domain.token.entity;

import com.concert.reservation.domain.exception.CustomException;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenRedis {
    private String uuid;
    private Long customerId;
    private Long expireDt;

    public static String toSerialize(String uuid, Long customerId) {
        return java.lang.String.format("%s:%s", uuid, customerId);
    }

    public static String toSerialize(String uuid, Long customerId, Long expireDt) {
        return String.format("%s:%s:%s", uuid, customerId, expireDt);
    }

    public static TokenRedis toTokenRedis(String serializedToken) {
        int countColon = serializedToken.split(":").length;

        String uuid = null;
        String customerId = null;
        String expireDt = null;

        // uuid, customerId만 존재하는 경우 ex) 15211673-fc49-4256-a84a-cc4b98694a30:1
        if (countColon == 2) {
            int firstColonIndex = serializedToken.indexOf(":");

            uuid = serializedToken.substring(0, firstColonIndex);
            customerId = serializedToken.substring(firstColonIndex + 1);
        }
        // uuid, customerId, expireDt 모두 존재하는 경우 ex) 15211673-fc49-4256-a84a-cc4b98694a30:1:1722530330027
        else if (countColon > 2) {
            int firstColonIndex = serializedToken.indexOf(":");
            int secondColonIndex = serializedToken.indexOf(":", firstColonIndex + 1);

            uuid = serializedToken.substring(0, firstColonIndex);
            customerId = serializedToken.substring(firstColonIndex + 1, secondColonIndex);
            expireDt = serializedToken.substring(secondColonIndex + 1);
        }
        else {
            throw new CustomException(HttpStatus.ACCEPTED, "토큰 포맷이 정확하지 않습니다.");
        }

        return TokenRedis.builder().uuid(uuid).customerId(Long.valueOf(customerId)).expireDt((expireDt != null) ? Long.valueOf(expireDt) : null).build();
    }
}