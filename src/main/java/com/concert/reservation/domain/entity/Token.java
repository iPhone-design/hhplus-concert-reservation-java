package com.concert.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "TOKEN")
@Getter
@Setter
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;
    @Column(name = "user_uuid")
    private Long userUuid;
    @Column(name = "queue")
    private Integer queue;
    @Column(name = "status")
    private String status;
    @Column(name = "expire_dt")
    private Timestamp expireDt;
}
