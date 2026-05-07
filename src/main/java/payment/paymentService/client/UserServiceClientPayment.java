package payment.paymentService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import payment.paymentService.client.dto.PaymentDTO;
import payment.paymentService.client.dto.UpdateBalanceRequest;


@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserServiceClientPayment {

    @GetMapping("/api/v1/users/userId")
    PaymentDTO getUserById(@RequestParam("userId") Long userId);

    @GetMapping("/api/v1/users/role")
    PaymentDTO getUserByRole(@RequestParam("role") String role);

    @GetMapping("/api/v1/users/userId/role")
    PaymentDTO getUserByUserIdAndRole(
            @RequestParam ("userId") Long userId,
            @RequestParam ("role") String role
    );

    @PatchMapping("/api/v1/users/balance")
    void updateBalance(
            @RequestParam("userId") Long userId,
            @RequestBody UpdateBalanceRequest request
    );
}
