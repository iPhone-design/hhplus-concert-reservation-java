package com.concert.reservation.presentation.customer;

import com.concert.reservation.application.customer.CustomerFacade;
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
    @GetMapping("/information/{customer-id}")
    public CustomerResponse getUserInfo(@PathVariable(name = "customer-id") Long customerId) {
        return CustomerResponse.toResponse(customerFacade.getUserInfo(customerId));
    }

    /**
     * 잔액 충전 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   customerId - 고객 ID
     *
     * @return  customerResponse
     */
    @PatchMapping("/charge/{customer-id}")
    public CustomerResponse chargeAmount(@PathVariable(name = "customer-id") Long customerId, @RequestBody CustomerRequest customerRequest) {
        return CustomerResponse.toResponse(customerFacade.chargeAmount(customerId, customerRequest.getAmount()));
    }
}
