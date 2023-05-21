package com.sadeghi.billpayment.repository;

import com.sadeghi.billpayment.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 8:21 PM
 */

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}