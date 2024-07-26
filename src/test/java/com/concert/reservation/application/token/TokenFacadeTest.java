package com.concert.reservation.application.token;

import com.concert.reservation.application.customer.CustomerFacade;
import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TokenFacadeTest {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenFacade tokenFacade;
    @Autowired
    private CustomerFacade customerFacade;

    @BeforeEach
    void setup () {
        tokenService.deleteAll();
        customerFacade.save(CustomerDomain.builder().customerName("김아무개").amount(1000L).build());
    }

    @Test
    @DisplayName("고객 토큰 발급")
    void issueToken() {
        // given
        Long customerId = 1L;

        // when
        TokenDomain tokenDomain = tokenFacade.issueToken(customerId);

        // then
        assertThat(customerId).isEqualTo(tokenDomain.getCustomerId());
    }

    @Test
    @DisplayName("고객 토큰 유효성 체크")
    void checkActiveStatus() {
        // given
        Long customerId = 1L;
        tokenFacade.issueToken(customerId);

        // when-then
        assertThatThrownBy(() -> {
            tokenFacade.checkActiveStatus(customerId);
        }).isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("토큰이 존재하고 첫 번째 순서의 대기열인 경우")
    void findByCustomerIdAndThenChangeStatusToActive_whenTokenExistsAndIsFirstWaiting() {
        // given
        Long customerId = 1L;
        tokenFacade.issueToken(customerId);

        // when
        TokenDomain result = tokenFacade.findByCustomerIdAndThenChangeStatusToActive(customerId);

        // then
        assertThat(TokenStatus.ACTIVE).isEqualTo(result.getStatus());
    }

    @Test
    @DisplayName("토큰이 존재하고 첫 번째 순서의 대기열이 아닌 경우")
    void findByCustomerIdAndThenChangeStatusToActive_whenTokenExistsAndIsNotFirstWaiting() {
        // given
        Integer rank = 2;
        Long customerId1 = 1L;
        Long customerId2 = 2L;
        customerFacade.save(CustomerDomain.builder().customerName("두번째").amount(1000L).build());
        tokenFacade.issueToken(customerId1);
        tokenFacade.issueToken(customerId2);

        // when
        TokenDomain result = tokenFacade.findByCustomerIdAndThenChangeStatusToActive(customerId2);

        // then
        assertThat(rank).isEqualTo(result.getRank());
    }

    @Test
    @DisplayName("토큰이 존재하지 않는 경우")
    void findByCustomerIdAndThenChangeStatusToActive_whenTokenDoesNotExist() {
        // given
        Long customerId = 3L;

        // when - then
        assertThatThrownBy(() -> tokenFacade.findByCustomerIdAndThenChangeStatusToActive(customerId)).isInstanceOf(CustomException.class);
    }
}