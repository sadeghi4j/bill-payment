package com.sadeghi.billpayment;

import com.sadeghi.billpayment.entity.Account;
import com.sadeghi.billpayment.entity.Bill;
import com.sadeghi.billpayment.entity.Payment;
import com.sadeghi.billpayment.exception.BillAlreadyPaidException;
import com.sadeghi.billpayment.exception.BillNotFoundException;
import com.sadeghi.billpayment.model.BillPayDto;
import com.sadeghi.billpayment.model.BillType;
import com.sadeghi.billpayment.repository.AccountRepository;
import com.sadeghi.billpayment.service.BillService;
import com.sadeghi.billpayment.service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/26/23 - 5:05 PM
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BillTest extends BaseTestClass {

    static final String BILL_ID = "1";
    static final String PAY_ID = "1";
    static final String RIGHT_USERNAME = "username";


    @Autowired
    private BillService billService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void givenBillId_whenFindBillByBillId_thenBillIsPresent() {
        Optional<Bill> optionalBill = billService.findBillByBillId(BILL_ID);
        Assertions.assertTrue(optionalBill.isPresent());
    }

    @Test
    void givenNotExistingBillId_whenFindBillByBillId_thenBillIsEmpty() {
        Optional<Bill> optionalBill = billService.findBillByBillId("13");
        Assertions.assertTrue(optionalBill.isEmpty());
    }

    @Test
    void givenPhoneNumber_whenFindBillByPhoneNumber_thenBillIsPresent() {
        Optional<Bill> optionalBill = billService.findBillByPhoneNumber("0912");
        Assertions.assertTrue(optionalBill.isPresent());
    }

    @Test
    void givenNotExistingPhoneNumber_whenFindBillByPhoneNumber_thenBillIsEmpty() {
        Optional<Bill> optionalBill = billService.findBillByPhoneNumber("0913");
        Assertions.assertTrue(optionalBill.isEmpty());
    }

    @Test
    void givenBillIdAndPayId_whenFindByBillIdAndPayId_thenBillIsPresent() {
        Optional<Bill> optionalBill = billService.findByBillIdAndPayId(BILL_ID, "1");
        Assertions.assertTrue(optionalBill.isPresent());
    }

    @Test
    void givenNotExistingBillIdAndPayId_whenFindByBillIdAndPayId_thenBillIsEmpty() {
        Optional<Bill> optionalBill = billService.findByBillIdAndPayId(BILL_ID, "2");
        Assertions.assertTrue(optionalBill.isEmpty());
    }

    @Test
    void givenBillIdentifierAndBillType_whenFindBill_thenBillIsPresent() {
        Optional<Bill> optionalBill = billService.findBill(BillType.WATER, BILL_ID);
        Assertions.assertTrue(optionalBill.isPresent());
    }

    @Test
    void givenWrongBillIdentifierAndBillType_whenFindBill_thenBillIsEmpty() {
        Optional<Bill> optionalBill = billService.findBill(BillType.WATER, "12");
        Assertions.assertTrue(optionalBill.isEmpty());
    }

    @Test
    void givenBillIdentifierAndWrongBillType_whenFindBill_thenBillIsEmpty() {
        Optional<Bill> optionalBill = billService.findBill(BillType.ELECTRICITY, BILL_ID);
        Assertions.assertTrue(optionalBill.isPresent());
    }

    @Test
    void givenWrongBillIdPayId_whenPayBill_thenThrowsBillNotFoundException() {
        BillPayDto dto = new BillPayDto(BILL_ID, "2", "1");
        Assertions.assertThrows(BillNotFoundException.class, () -> billService.billPay(dto));
    }

    @Test
    void givenBillIdPayId_whenPayBillAndPaymentExists_thenThrowsBillAlreadyPaidException() {
        Payment payment = Payment.builder().billId(BILL_ID).payId(PAY_ID).billType(BillType.WATER).amount(1000).build();
        paymentService.save(payment);

        BillPayDto dto = new BillPayDto("1", "1", "1");
        Assertions.assertThrows(BillAlreadyPaidException.class, () -> billService.billPay(dto));
    }

    @Test
    void givenBillIdPayId_whenPayBill_thenWithdrawAndSavePayment() {
        UserDetails user = new User(RIGHT_USERNAME, "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "credential"));

        BillPayDto dto = new BillPayDto(BILL_ID, PAY_ID, "1");
        billService.billPay(dto);

        Optional<Account> optionalAccount = accountRepository.findByUsername(RIGHT_USERNAME);
        Assertions.assertTrue(optionalAccount.isPresent());
        Assertions.assertEquals(0, optionalAccount.get().getBalance().compareTo(new BigDecimal(0)));

        Optional<Payment> optionalPayment = paymentService.findByBillIdAndPayId(BILL_ID, PAY_ID);
        Assertions.assertTrue(optionalPayment.isPresent());
    }

    @Test
    void givenConcurrentBillPayment_whenSaveConcurrentAccount_thenThrowsObjectOptimisticLockingFailureException() {
        Optional<Account> optionalAccount = accountRepository.findByUsername(RIGHT_USERNAME);
        Assertions.assertTrue(optionalAccount.isPresent());
        Account account1 = optionalAccount.get();
        account1.setBalance(account1.getBalance().subtract(new BigDecimal(1000)));

        Optional<Account> optionalAccount2 = accountRepository.findByUsername(RIGHT_USERNAME);
        Assertions.assertTrue(optionalAccount2.isPresent());
        Account account2 = optionalAccount2.get();
        account2.setBalance(account2.getBalance().subtract(new BigDecimal(1000)));

        accountRepository.save(account1);
        Assertions.assertThrows(ObjectOptimisticLockingFailureException.class, () -> accountRepository.save(account2));

        Optional<Account> optionalAccountAfterChange = accountRepository.findByUsername(RIGHT_USERNAME);
        Assertions.assertTrue(optionalAccountAfterChange.isPresent());
        Assertions.assertEquals(0, optionalAccountAfterChange.get().getBalance().compareTo(new BigDecimal(0)));

    }
}
