package com.sadeghi.billpayment.entity;

import com.sadeghi.billpayment.model.BillType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 8:11 PM
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
        indexes = {@Index(columnList = "billId, payId")},
        uniqueConstraints = @UniqueConstraint(name = "UNIQUE_PAYMENT_BILL_ID_PAY_ID", columnNames = {"billId", "payId"})
)
public class Payment extends BaseEntity<Long> {


    @Column(nullable = false, length = 13)
    String billId;

    @Column(nullable = false, length = 13)
    String payId;

    @Column(nullable = false)
    Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    BillType billType;

}
