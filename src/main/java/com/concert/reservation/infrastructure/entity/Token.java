package com.concert.reservation.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "waiting_start_dt")
    @CreationTimestamp
    private Timestamp waitingStartDt;
    @Column(name = "entry_dt")
    private Timestamp entryDt;
}
