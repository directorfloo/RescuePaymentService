package payment.paymentService.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
    @Builder
    @Data
    public class CreateWithdrawRequest {
        private Long bankAccountId;
        private Long userId;
        private String bankName;
        private String accountNumber;
        private String accountFirstName;
        private String accountLastName;
        private BigDecimal amount;
        private LocalDateTime createdAt;
        private String  pin;
}
