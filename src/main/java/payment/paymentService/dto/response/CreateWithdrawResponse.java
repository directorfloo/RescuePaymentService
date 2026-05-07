package payment.paymentService.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreateWithdrawResponse {
    private Long debitId;
    private Long bankAccountId;
    private Long userId;
    private String bankName;
    private String accountNumber;
    private String accountFirstName;
    private String accountLastName;
    private BigDecimal amount;
    private String createdAt;
}
