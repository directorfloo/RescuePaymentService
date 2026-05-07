package payment.paymentService.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreditRequest {
    private Long userId;
    private Long bankAccountId;
    private BigDecimal amount;
    private String creditorFirstName;
    private String creditorLastName;
    private String pin;
    private String createdAt;
}
