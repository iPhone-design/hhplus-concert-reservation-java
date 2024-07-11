package com.concert.reservation.presentation.customer;

import com.concert.reservation.application.customer.CustomerFacade;
import com.concert.reservation.domain.customer.CustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerFacade customerFacade;

    /**
     * 잔액 조회 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   customerId - 고객 ID
     * @return  customerResponse
     */
    @GetMapping("/{customer-id}/amount")
    public CustomerResponse detailAmount(@PathVariable(name = "customer-id") Long customerId) {
        return CustomerCommand.toResponse(customerFacade.detailAmount(customerId));
    }

    /**
     * 잔액 충전 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   customerRequest - 고객 요청
     * @return  customerResponse
     */
    @PatchMapping("/charge")
    public CustomerResponse chargeAmount(@RequestBody CustomerRequest customerRequest) {
        return CustomerCommand.toResponse(customerFacade.chargeAmount(CustomerCommand.toDomain(customerRequest)));
    }
}
