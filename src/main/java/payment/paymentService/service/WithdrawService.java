package payment.paymentService.service;

import payment.paymentService.dto.request.CreateWithdrawRequest;
import payment.paymentService.dto.response.CreateWithdrawResponse;

public interface WithdrawService {
    CreateWithdrawResponse createWithdraw(CreateWithdrawRequest request);
}
