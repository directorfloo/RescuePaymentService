package payment.paymentService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.paymentService.dto.request.ViewCreditTransactionRequest;
import payment.paymentService.dto.request.ViewDebitTransactionRequest;
import payment.paymentService.dto.response.ApiResponse;
import payment.paymentService.dto.response.ViewCreditTransactionResponse;
import payment.paymentService.dto.response.ViewDebitTransactionResponse;
import payment.paymentService.exception.RescueNowException;
import payment.paymentService.service.ViewTransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor

public class ViewTransactionController {

    @Autowired
    private ViewTransactionService viewTransactionService;

    @PreAuthorize("hasAnyRole('FIRSTAIDER', 'ADMIN')")
    @GetMapping("/ViewCreditTransaction")
    public ResponseEntity<ApiResponse> viewCreditTransaction(
            @RequestBody ViewCreditTransactionRequest request) {
        try {
            ViewCreditTransactionResponse response = viewTransactionService.viewCreditTransaction(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (RescueNowException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('FIRSTAIDER', 'ADMIN')")
    @GetMapping("/ViewDebitTransaction")
    public ResponseEntity<ApiResponse> viewDebitTransaction(
            @RequestBody ViewDebitTransactionRequest request) {
        try {
            ViewDebitTransactionResponse response = viewTransactionService.viewDebitTransaction(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (RescueNowException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/creditAllTransaction")
    public ResponseEntity<ApiResponse> getAllCreditTransactions() {
        try {
            List<ViewCreditTransactionResponse> response = viewTransactionService.getCreditAllTransactions();
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (RescueNowException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/debitAllTransaction")
    public ResponseEntity<ApiResponse> getAllDebitTransactions() {
        try {
            List<ViewDebitTransactionResponse> response = viewTransactionService.getDebitAllTransactions();
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (RescueNowException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
