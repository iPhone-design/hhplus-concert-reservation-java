package com.concert.reservation.domain.seat.entity;

import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT_OPTION")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_option_id")
    private Long seatOptionId;
    @Version
    private long version;
    @Column(name = "seat_id")
    private Long seatId;
    @Column(name = "concert_option_id")
    private Long concertOptionId;
    @Column(name = "price")
    private Long price;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SeatOptionStatus status;

    public SeatOptionDomain toDomain() {
        return SeatOptionDomain.builder()
                               .seatOptionId(this.seatOptionId)
                               .version(this.version)
                               .seatId(this.seatId)
                               .concertOptionId(this.concertOptionId)
                               .price(this.price)
                               .status(status)
                               .build();
    }

    public static SeatOption toEntity(SeatOptionDomain seatOptionDomain) {
        return SeatOption.builder()
                         .seatOptionId(seatOptionDomain.getSeatOptionId())
                         .seatId(seatOptionDomain.getSeatId())
                         .concertOptionId(seatOptionDomain.getConcertOptionId())
                         .price(seatOptionDomain.getPrice())
                         .status(seatOptionDomain.getStatus())
                         .build();
    }
}
