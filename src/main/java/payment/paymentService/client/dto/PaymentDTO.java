package payment.paymentService.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {

    private Long bankAccountId;
    private  Long userId;
    private String bankName;
    private String accountNumber;
    private String accountFirstName;
    private String accountLastName;
    private LocalDateTime createdAt;
    private String pin ;
    private BigDecimal amount;
    private  Long  DebitId;
    private  Long  CreditId;
    private String creditorFirstName;
    private String creditorLastName;
    private String FirstName;
    private String LastName;
    private BigDecimal balance;

}
