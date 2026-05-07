package payment.paymentService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.paymentService.client.NotificationPaymentServiceClient;
import payment.paymentService.client.UserServiceClientPayment;
import payment.paymentService.client.dto.*;
import payment.paymentService.data.model.BankAccount;
import payment.paymentService.data.repository.BankAccountRepository;
import payment.paymentService.dto.request.CreditRequest;
import payment.paymentService.dto.response.CreditResponse;
import payment.paymentService.exception.InvalidCredentials;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static payment.paymentService.util.Mapper.mapToCreditResponse;


@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService{
    @Autowired
    private UserServiceClientPayment userServiceClientPayment;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private NotificationPaymentServiceClient notificationPaymentServiceClient;

    @Override
    public CreditResponse credit(CreditRequest request) {
        PaymentDTO admin = userServiceClientPayment.getUserByUserIdAndRole(request.getUserId(), "ADMIN");
        if(admin == null) {
            throw new InvalidCredentials("Admin not found");
        }

        PaymentDTO firstAider = userServiceClientPayment.getUserByUserIdAndRole(request.getUserId(), "FIRST_AIDER");
        if(firstAider == null) {
            throw new InvalidCredentials("FirstAider not found");
        }

        BankAccount bank = bankAccountRepository.findById(request.getBankAccountId()).orElseThrow();

        BankAccount account = BankAccount.builder()
                .accountFirstName(request.getCreditorFirstName())
                .accountLastName(request.getCreditorLastName())
                .bankAccountId(bank.getBankAccountId())
                .amount(request.getAmount())
                .pin(request.getPin())
                .createdAt(LocalDateTime.now())
                .build();
        if(!admin.getPin().equals(request.getPin()) &&
                admin.getFirstName().equals(request.getCreditorFirstName())&&
                admin.getLastName().equals(request.getCreditorLastName())){
            throw new InvalidCredentials("Invalid credentials");
        }
        BankAccount bankAccount = bankAccountRepository
                .findByBankAccountIdAndUserIdAndRole(request.getBankAccountId(), request.getUserId(), UserRole.FIRST_AIDER)
                .orElseThrow(() -> new InvalidCredentials(
                        "Bank account not found or does not belong to your account."));
        BigDecimal newBalance = firstAider.getBalance().add(request.getAmount());
        userServiceClientPayment.updateBalance(
                firstAider.getUserId(),
                UpdateBalanceRequest.builder()
                        .balance(newBalance)
                        .build()
        );

        BankAccount savedBank = bankAccountRepository.save(account);

        notificationPaymentServiceClient.notifyUser(
                NotifyUserPaymentRequest.builder()
                        .userId(firstAider.getUserId())
                        .type("CREDIT_RECEIVED")
                        .payload(Map.of(
                                "message", "Your account has been credited",
                                "amount", savedBank.getAmount(),
                                "bankName", savedBank.getBankName(),
                                "accountNumber", savedBank.getAccountNumber(),
                                "createdAt", savedBank.getCreatedAt()
                        ))
                        .build()
        );


        notificationPaymentServiceClient.broadcastToAdmins(
                BroadcastPaymentRequest.builder()
                        .type("CREDIT_CREATED")
                        .payload(Map.of(
                                "message", "Credit transaction created",
                                "firstAiderId", firstAider.getUserId(),
                                "amount", savedBank.getAmount(),
                                "createdAt", savedBank.getCreatedAt()
                        ))
                        .build()
        );

        return mapToCreditResponse(savedBank);
    }
}
