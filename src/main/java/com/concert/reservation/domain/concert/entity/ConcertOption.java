package com.concert.reservation.domain.concert.entity;

import com.concert.reservation.domain.concert.ConcertOptionDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_OPTION", indexes = {@Index(name = "index_concert_option", columnList = "open_dt")})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_option_id")
    private Long concertOptionId;
    @Column(name = "concert_id")
    private Long concertId;
    @Column(name = "concert_name")
    private String concertName;
    @Column(name = "location")
    private String location;
    @Column(name = "open_dt")
    private LocalDateTime openDt;

    public ConcertOptionDomain toDomain() {
        return ConcertOptionDomain.builder()
                                  .concertOptionId(this.concertOptionId)
                                  .concertId(this.concertId)
                                  .concertName(this.concertName)
                                  .location(this.location)
                                  .openDt((this.openDt))
                                  .build();
    }
}
