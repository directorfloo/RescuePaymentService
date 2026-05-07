package payment.paymentService.dto.response;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ViewDebitTransactionResponse {
    private Long bankAccountId;
    private String bankName;
    private String accountNumber;
    private String accountFirstName;
    private BigDecimal amount;
    private String accountLastName;
    private String createdAt;
}
