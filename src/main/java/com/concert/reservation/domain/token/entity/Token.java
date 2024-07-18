package com.concert.reservation.domain.token.entity;

import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TOKEN")
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;
    @Column(name = "customer_id")
    private Long customerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TokenStatus status;
    @Column(name = "waiting_start_dt")
    @CreationTimestamp
    private LocalDateTime waitingStartDt;
    @Column(name = "entry_dt")
    private LocalDateTime entryDt;

    public TokenDomain toDomain() {
        return TokenDomain.builder()
                .tokenId(this.tokenId)
                .customerId(this.customerId)
                .status(this.status)
                .waitingStartDt(this.waitingStartDt)
                .entryDt(this.entryDt)
                .build();
    }

    public static Token toEntity(TokenDomain tokenDomain) {
        return Token.builder()
                .tokenId(tokenDomain.getTokenId())
                .customerId(tokenDomain.getCustomerId())
                .status(tokenDomain.getStatus())
                .waitingStartDt(tokenDomain.getWaitingStartDt())
                .entryDt(tokenDomain.getEntryDt())
                .build();
    }
}
