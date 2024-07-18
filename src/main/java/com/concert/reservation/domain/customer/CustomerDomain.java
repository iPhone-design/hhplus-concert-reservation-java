package com.concert.reservation.domain.customer;

import com.concert.reservation.domain.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDomain {
    private Long customerId;
    private String customerName;
    private Long amount;

    // 잔액 추가
    public void plusAmount(Long amount) {
        this.amount += amount;
    }

    // 잔액 차감
    public void minusAmount(Long amount) {
        if (this.amount < amount) {
            throw new CustomException(HttpStatus.ACCEPTED, "금액이 부족합니다.");
        }

        this.amount -= amount;
    }
}
