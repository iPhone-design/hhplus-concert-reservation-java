package com.concert.reservation.infrastructure.token.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "TOKEN")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "status")
    private String status;
    @Column(name = "expire_dt")
    private Timestamp expireDt;
}
