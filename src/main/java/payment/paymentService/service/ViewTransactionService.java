package payment.paymentService.service;

import payment.paymentService.dto.request.ViewCreditTransactionRequest;
import payment.paymentService.dto.request.ViewDebitTransactionRequest;
import payment.paymentService.dto.response.ViewCreditTransactionResponse;
import payment.paymentService.dto.response.ViewDebitTransactionResponse;

import java.util.List;

public interface ViewTransactionService {
    ViewCreditTransactionResponse viewCreditTransaction(ViewCreditTransactionRequest request);
    ViewDebitTransactionResponse viewDebitTransaction(ViewDebitTransactionRequest debit);
    List<ViewCreditTransactionResponse> getCreditAllTransactions();
    List<ViewDebitTransactionResponse> getDebitAllTransactions();
}
