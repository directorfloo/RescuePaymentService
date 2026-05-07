package payment.paymentService.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BroadcastPaymentRequest {
    private String type;
    private Object payload;
}
