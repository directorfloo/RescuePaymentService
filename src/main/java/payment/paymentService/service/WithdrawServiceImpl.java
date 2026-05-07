package payment.paymentService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.paymentService.client.NotificationPaymentServiceClient;
import payment.paymentService.client.UserServiceClientPayment;
import payment.paymentService.client.dto.BroadcastPaymentRequest;
import payment.paymentService.client.dto.PaymentDTO;
import payment.paymentService.client.dto.UpdateBalanceRequest;
import payment.paymentService.client.dto.UserRole;
import payment.paymentService.data.model.BankAccount;
import payment.paymentService.data.repository.BankAccountRepository;
import payment.paymentService.dto.request.CreateWithdrawRequest;
import payment.paymentService.dto.response.CreateWithdrawResponse;
import payment.paymentService.exception.InvalidCredentials;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static payment.paymentService.util.Mapper.mapToCreateWithdrawResponse;

@Service
@RequiredArgsConstructor
public class WithdrawServiceImpl implements WithdrawService{


    @Autowired
    private UserServiceClientPayment userServiceClientPayment;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private NotificationPaymentServiceClient notificationPaymentServiceClient;




    @Override
    public CreateWithdrawResponse createWithdraw(CreateWithdrawRequest request) {
        PaymentDTO firstAider = userServiceClientPayment.getUserByUserIdAndRole(request.getUserId(), "FIRST_AIDER");
        if (firstAider == null) {
            throw new InvalidCredentials("FirstAider not found");
        }

        BankAccount bank =bankAccountRepository.findById(request.getBankAccountId()).orElseThrow();
        BankAccount account  = BankAccount.builder()
                .bankName(request.getBankName())
                .accountNumber(request.getAccountNumber())
                .accountFirstName(request.getAccountFirstName())
                .accountLastName(request.getAccountLastName())
                .amount(request.getAmount())
                .pin(request.getPin())
                .createdAt(LocalDateTime.now())
                .build();

        if(!firstAider.getPin().equals(request.getPin())){
            throw new InvalidCredentials("Entered pin is incorrect");
        }
        BankAccount bankAccount = bankAccountRepository
                .findByBankAccountIdAndUserIdAndRole(request.getBankAccountId(), firstAider.getUserId(), UserRole.FIRST_AIDER)
                .orElseThrow(() -> new InvalidCredentials(
                        "Bank account not found or does not belong to your account."));
        if(firstAider.getBalance().compareTo(request.getAmount())<0){
            throw new InvalidCredentials("Insufficient balance");
        }
        if(firstAider.getBalance().compareTo(request.getAmount())==0){
            throw new InvalidCredentials("Insufficient balance");
        }
        if (firstAider.getFirstName().equals(request.getAccountFirstName()) &&
                firstAider.getLastName().equals(request.getAccountLastName())){
            throw new InvalidCredentials("Account name cannot be same as first name and last name");
        }

        BigDecimal newBalance = firstAider.getBalance().subtract(request.getAmount());
        userServiceClientPayment.updateBalance(
                firstAider.getUserId(),
                UpdateBalanceRequest.builder()
                        .balance(newBalance)
                        .build()
        );

        BankAccount savedBank =  bankAccountRepository.save(account);
        notificationPaymentServiceClient.broadcastToAdmins(
                BroadcastPaymentRequest.builder()
                        .type("WITHDRAW_CREATED")
                        .payload(Map.of(
                                "id", savedBank.getUserId(),
                                "bankName", savedBank.getBankName(),
                                "accountNumber", savedBank.getAccountNumber(),
                                "accountFirstName", savedBank.getAccountFirstName(),
                                "accountLastName", savedBank.getAccountLastName(),
                                "amount", savedBank.getAmount(),
                                "createdAt", savedBank.getCreatedAt()
                        ))
                        .build()
        );
        return mapToCreateWithdrawResponse(savedBank);
    }
}


