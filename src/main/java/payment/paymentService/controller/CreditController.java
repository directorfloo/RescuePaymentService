package payment.paymentService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.paymentService.dto.request.CreditRequest;
import payment.paymentService.dto.response.ApiResponse;
import payment.paymentService.dto.response.CreditResponse;
import payment.paymentService.exception.RescueNowException;
import payment.paymentService.service.CreditService;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
public class CreditController {


    @Autowired
    private CreditService creditService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/creditAlert")
    public ResponseEntity<ApiResponse> credit(
            @RequestBody CreditRequest request) {
        try {

            CreditResponse response = creditService.credit(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);

        } catch (RescueNowException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

