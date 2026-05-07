package payment.paymentService.util;

import payment.paymentService.data.model.BankAccount;
import payment.paymentService.dto.response.CreateWithdrawResponse;
import payment.paymentService.dto.response.CreditResponse;
import payment.paymentService.dto.response.ViewCreditTransactionResponse;
import payment.paymentService.dto.response.ViewDebitTransactionResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static CreateWithdrawResponse mapToCreateWithdrawResponse(BankAccount bank) {
        CreateWithdrawResponse response = CreateWithdrawResponse.builder()
                .debitId(bank.getDebitId())
                .bankAccountId(bank.getBankAccountId())
                .userId(bank.getUserId())
                .bankName(bank.getBankName())
                .accountNumber(bank.getAccountNumber())
                .accountFirstName(bank.getAccountFirstName())
                .accountLastName(bank.getAccountLastName())
                .amount(bank.getAmount())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        return response;
    }

    public static CreditResponse mapToCreditResponse(BankAccount bank) {
        CreditResponse creditResponse = CreditResponse.builder()
                .creditId(bank.getCreditId())
                .bankAccountId(bank.getBankAccountId())
                .amount(bank.getAmount())
                .creditorFirstName(bank.getCreditorFirstName())
                .creditorLastName(bank.getCreditorLastName())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        return creditResponse;
    }

    public static ViewCreditTransactionResponse mapToCredit(BankAccount bank) {
        ViewCreditTransactionResponse creditResponse = ViewCreditTransactionResponse.builder()
                .bankAccountId(bank.getBankAccountId())
                .creditorFirstName(bank.getCreditorFirstName())
                .creditorLastName(bank.getCreditorLastName())
                .amount(bank.getAmount())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        return creditResponse;

    }

    public static ViewDebitTransactionResponse mapToDebit(BankAccount bank) {
        ViewDebitTransactionResponse debitResponse = ViewDebitTransactionResponse.builder()
                .bankAccountId(bank.getBankAccountId())
                .bankName(bank.getBankName())
                .accountNumber(bank.getAccountNumber())
                .accountFirstName(bank.getAccountFirstName())
                .accountLastName(bank.getAccountLastName())
                .amount(bank.getAmount())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        return debitResponse;
    }

}
