package payment.paymentService.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ViewCreditTransactionResponse {
    private Long bankAccountId;
    private String creditorFirstName;
    private String creditorLastName;
    private String createdAt;
    private BigDecimal amount;
}
