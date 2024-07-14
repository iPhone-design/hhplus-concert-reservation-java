package com.concert.reservation.domain.token.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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
    @Column(name = "status")
    private String status;
    @Column(name = "waiting_start_dt")
    @CreationTimestamp
    private Timestamp waitingStartDt;
    @Column(name = "entry_dt")
    private Timestamp entryDt;
}
