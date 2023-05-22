package com.sadeghi.billpayment.repository;

import com.sadeghi.billpayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 8:22 PM
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBillIdAndPayId(String billId, String payId);

}