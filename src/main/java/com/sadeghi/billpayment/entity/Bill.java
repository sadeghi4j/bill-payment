package com.sadeghi.billpayment.entity;

import com.sadeghi.billpayment.model.BillType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 7:24 PM
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
        indexes = {@Index(columnList = "billId")}
)
public class Bill extends BaseEntity<Long> {

    @Column(nullable = false, unique = true, length = 13)
    String billId;

    @Column(nullable = false, length = 13)
    String payId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    BillType billType;

    @Column(nullable = false)
    Integer amount;

}
