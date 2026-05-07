package payment.paymentService.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payment.paymentService.client.dto.UserRole;
import payment.paymentService.data.model.BankAccount;

import java.util.List;
import java.util.Optional;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    Optional<BankAccount> findByBankAccountIdAndUserIdAndRole(Long bankAccountId, Long userId, UserRole role);
}
