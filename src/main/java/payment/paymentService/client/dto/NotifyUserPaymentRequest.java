package payment.paymentService.client.dto;

import lombok.Builder;
import lombok.Data;

    @Data
    @Builder
    public class NotifyUserPaymentRequest {
        private Long userId;
        private String type;
        private Object payload;
}
