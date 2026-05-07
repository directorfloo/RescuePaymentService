package payment.paymentService.service;

import payment.paymentService.dto.request.CreditRequest;
import payment.paymentService.dto.response.CreditResponse;

public interface CreditService {
    CreditResponse credit(CreditRequest request);
}
