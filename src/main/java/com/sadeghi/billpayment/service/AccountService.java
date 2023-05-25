package com.sadeghi.billpayment.service;

import com.sadeghi.billpayment.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author Ali Sadeghi
 * Created at 5/25/23 - 10:34 PM
 */

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class AccountService {

    final AccountRepository accountRepository;

}
