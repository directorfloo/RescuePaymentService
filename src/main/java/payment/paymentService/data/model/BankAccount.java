package payment.paymentService.data.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccountId;

    private  Long userId;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumber;

    private  String role;

    @Column(nullable = false)
    private String accountFirstName;

    @Column(nullable = false)
    private String accountLastName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String pin ;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(unique = true )
    private  Long  debitId;

    @Column(unique = true)
    private  Long  creditId;

    @Column(nullable = false)
    private String creditorFirstName;

    @Column(nullable = false)
    private String creditorLastName;
}
