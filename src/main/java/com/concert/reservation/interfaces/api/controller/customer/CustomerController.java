package com.concert.reservation.interfaces.api.controller.customer;

import com.concert.reservation.application.customer.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerFacade customerFacade;

    /**
     * 고객 생성 API
     *
     * @author  양종문
     * @since   2024-07-21
     * @param   customerRequest - 고객 신청
     * @return  customerResponse
     */
    @PostMapping("/create")
    public CustomerResponse save(@RequestBody CustomerRequest customerRequest) {
        return CustomerResponse.toResponse(customerFacade.save(CustomerRequest.toDomain(customerRequest)));
    }

    /**
     * 고객 상세조회 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   customerId - 고객 ID
     * @return  customerResponse
     */
    @GetMapping("/information/{customer-id}")
    public CustomerResponse findById(@PathVariable(name = "customer-id") Long customerId) {
        return CustomerResponse.toResponse(customerFacade.findById(customerId));
    }

    /**
     * 잔액 충전 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   customerId - 고객 ID
     * @return  customerResponse
     */
    @PatchMapping("/charge/{customer-id}")
    public CustomerResponse chargeAmount(@PathVariable(name = "customer-id") Long customerId, @RequestBody CustomerRequest customerRequest) {
        return CustomerResponse.toResponse(customerFacade.chargeAmount(customerId, customerRequest.getAmount()));
    }
}
