package com.sadeghi.billpayment.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Ali Sadeghi
 * Created at 5/25/23 - 10:34 PM
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(
        indexes = {
                @Index(columnList = "username"),
                @Index(columnList = "accountNumber")
        }
)
public class Account extends BaseEntity<Long> {

    @Column(nullable = false, unique = true, length = 20)
    String username;

    @Column(nullable = false, unique = true, length = 20)
    String accountNumber;

    @Column(nullable = false, precision = 19, scale = 4)
    BigDecimal balance;

}
