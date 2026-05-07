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
import payment.paymentService.dto.request.CreateWithdrawRequest;
import payment.paymentService.dto.response.ApiResponse;
import payment.paymentService.dto.response.CreateWithdrawResponse;
import payment.paymentService.exception.RescueNowException;
import payment.paymentService.service.WithdrawService;

@RestController
@RequestMapping("/api/v1/withdraw")
@RequiredArgsConstructor
public class WithdrawServiceController {

    @Autowired
    private WithdrawService withdrawService;

    @PreAuthorize("hasRole('FIRST_AIDER')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createWithdraw(
            @RequestBody CreateWithdrawRequest request) {
        try {
            CreateWithdrawResponse response = withdrawService.createWithdraw(request);

            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);

        } catch (RescueNowException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
