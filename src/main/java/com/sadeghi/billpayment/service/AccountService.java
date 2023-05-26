package com.sadeghi.billpayment.service;

import com.sadeghi.billpayment.entity.Account;
import com.sadeghi.billpayment.exception.AccountIsNotSelfieException;
import com.sadeghi.billpayment.exception.AccountNotFoundException;
import com.sadeghi.billpayment.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    /**
     * finds account by username and if exits check its account number with the given account number in billpayDto
     * -
     *
     * @param accountNumber Account Number
     * @return account corresponding to given account number
     */
    public Account checkAccountIsSelfie(String accountNumber) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = findByUsername(userDetails.getUsername()).orElseThrow(AccountNotFoundException::new);
        if (!account.getAccountNumber().equals(accountNumber)) throw new AccountIsNotSelfieException();
        return account;
    }

    public void withdraw(Account account, BigDecimal price) {
        BigDecimal newBalance = account.getBalance().subtract(price);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

}
