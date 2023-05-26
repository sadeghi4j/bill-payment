package com.sadeghi.billpayment;

import com.sadeghi.billpayment.entity.Account;
import com.sadeghi.billpayment.exception.AccountIsNotSelfieException;
import com.sadeghi.billpayment.exception.AccountNotFoundException;
import com.sadeghi.billpayment.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

@Log4j2
class ApplicationTests extends BaseTestClass {

    static final String RIGHT_USERNAME = "username";

    @Autowired
    private AccountService accountService;

    @Test
    void givenExistingAccount_whenFindByUsername_thenAccountIsPresent() {
        Optional<Account> optionalAccount = accountService.findByUsername(RIGHT_USERNAME);
        Assertions.assertTrue(optionalAccount.isPresent());
    }

    @Test
    void givenNotExistingAccount_whenFindByUsername_thenAccountIsEmpty() {
        Optional<Account> optionalAccount = accountService.findByUsername("notExistingUsername");
        Assertions.assertTrue(optionalAccount.isEmpty());
    }

    @Test
    void givenExistingAccount_whenCheckAccountIsSelfie_thenReturnAccount() {
        UserDetails user = new User(RIGHT_USERNAME, "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "credential"));

        Account account = accountService.checkAccountIsSelfie("1");
        Assertions.assertNotNull(account);
    }

    @Test
    void givenNotExistingAccount_whenCheckAccountIsSelfie_thenThrowsAccountIsNotSelfieException() {
        UserDetails user = new User(RIGHT_USERNAME, "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "credential"));

        Assertions.assertThrows(AccountIsNotSelfieException.class, () -> accountService.checkAccountIsSelfie("2"));
    }

    @Test
    void givenWrongToken_whenCheckAccountIsSelfie_thenThrowsAccountNotFoundException() {
        UserDetails user = new User("wrongUsername", "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "credential"));

        Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.checkAccountIsSelfie("2"));
    }

}
