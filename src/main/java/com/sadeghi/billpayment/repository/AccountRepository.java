package com.sadeghi.billpayment.repository;

import com.sadeghi.billpayment.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ali Sadeghi
 * Created at 5/25/23 - 10:34 PM
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}