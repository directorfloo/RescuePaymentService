package payment.paymentService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.paymentService.data.model.BankAccount;
import payment.paymentService.data.repository.BankAccountRepository;
import payment.paymentService.dto.request.ViewCreditTransactionRequest;
import payment.paymentService.dto.request.ViewDebitTransactionRequest;
import payment.paymentService.dto.response.ViewCreditTransactionResponse;
import payment.paymentService.dto.response.ViewDebitTransactionResponse;
import payment.paymentService.util.Mapper;

import java.util.ArrayList;
import java.util.List;

import static payment.paymentService.util.Mapper.mapToCredit;
import static payment.paymentService.util.Mapper.mapToDebit;


@Service
@RequiredArgsConstructor
public class ViewTransactionServiceImpl implements  ViewTransactionService{

    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Override
    public ViewCreditTransactionResponse viewCreditTransaction(ViewCreditTransactionRequest request) {
        BankAccount creditTransaction = bankAccountRepository.findById(request.getCreditId()).orElseThrow();
        return mapToCredit(creditTransaction);
    }

    @Override
    public ViewDebitTransactionResponse viewDebitTransaction(ViewDebitTransactionRequest debit) {
        BankAccount debitTransaction = bankAccountRepository.findById(debit.getDebitId()).orElseThrow();
        return mapToDebit(debitTransaction);
    }

    @Override
    public List<ViewCreditTransactionResponse> getCreditAllTransactions() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<ViewCreditTransactionResponse> responseList = new ArrayList<>();
        for (BankAccount bankAccount : bankAccounts) {
            ViewCreditTransactionResponse response = Mapper.mapToCredit(bankAccount);
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public List<ViewDebitTransactionResponse> getDebitAllTransactions() {
        List<BankAccount> bankAccount = bankAccountRepository.findAll();
        List<ViewDebitTransactionResponse> responseList = new ArrayList<>();
        for (BankAccount bankAccounts : bankAccount) {
            ViewDebitTransactionResponse response = Mapper.mapToDebit(bankAccounts);
            responseList.add(response);
        }
        return responseList;
    }
}
