package payment.paymentService.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreditResponse {
    private Long creditId;
    private Long bankAccountId;
    private BigDecimal amount;
    private String creditorFirstName;
    private String creditorLastName;
    private String createdAt;
}
