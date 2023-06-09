package com.sadeghi.billpayment.service;

import com.sadeghi.billpayment.entity.Account;
import com.sadeghi.billpayment.entity.Bill;
import com.sadeghi.billpayment.entity.Payment;
import com.sadeghi.billpayment.exception.BillAlreadyPaidException;
import com.sadeghi.billpayment.exception.BillNotFoundException;
import com.sadeghi.billpayment.exception.BillPaymentException;
import com.sadeghi.billpayment.exception.InProgressPaymentException;
import com.sadeghi.billpayment.mapper.DTOMapper;
import com.sadeghi.billpayment.model.BillPayDto;
import com.sadeghi.billpayment.model.BillType;
import com.sadeghi.billpayment.repository.BillRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 9:02 PM
 */

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class BillService {

    final BillRepository billRepository;
    final PaymentService paymentService;
    final AccountService accountService;

    public Optional<Bill> findBillByBillId(String billId) {
        return billRepository.findByBillId(billId);
    }

    public Optional<Bill> findBillByPhoneNumber(String phoneNumber) {
        return billRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Bill> findByBillIdAndPayId(String billId, String payId) {
        return billRepository.findByBillIdAndPayId(billId, payId);
    }

    public Optional<Bill> findBill(BillType billType, String identifier) {
        return billType == BillType.MOBILE_PHONE ? findBillByPhoneNumber(identifier) : findBillByBillId(identifier);
    }

    @Transactional
    public void billPay(BillPayDto dto) {
        Bill bill = findByBillIdAndPayId(dto.billId(), dto.payId()).orElseThrow(BillNotFoundException::new);
        Optional<Payment> optionalPayment = paymentService.findByBillIdAndPayId(dto.billId(), dto.payId());
        if (optionalPayment.isPresent()) {
            throw new BillAlreadyPaidException();
        }

        Account account = accountService.checkAccountIsSelfie(dto.accountNumber());
        try {
            accountService.withdraw(account, BigDecimal.valueOf(bill.getAmount()));
            Payment payment = DTOMapper.INSTANCE.convertBillToPayment(bill);
            paymentService.save(payment);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.error(e);
            throw new InProgressPaymentException();
        } catch (DataIntegrityViolationException e) {
            log.error(e);
            throw new BillAlreadyPaidException();
        } catch (Exception e) {
            log.error(e);
            throw new BillPaymentException();
        }

    }

}
