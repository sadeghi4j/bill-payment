package com.sadeghi.billpayment.repository;

import com.sadeghi.billpayment.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 8:21 PM
 */

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByBillId(String billId);

    Optional<Bill> findByPhoneNumber(String phoneNumber);

    Optional<Bill> findByBillIdAndPayId(String billId, String payId);

}