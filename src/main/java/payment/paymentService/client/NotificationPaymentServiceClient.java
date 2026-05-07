package payment.paymentService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import payment.paymentService.client.dto.BroadcastPaymentRequest;
import payment.paymentService.client.dto.NotifyUserPaymentRequest;

@Service
@FeignClient(name = "notification-service", url = "${notification-service.url}")
public interface NotificationPaymentServiceClient {

    @PostMapping("/api/v1/notifications/notify-user")
    void notifyUser(@RequestBody NotifyUserPaymentRequest request);

    @PostMapping("/api/v1/notifications/broadcast-admin")
    void broadcastToAdmins(@RequestBody BroadcastPaymentRequest request);
}
