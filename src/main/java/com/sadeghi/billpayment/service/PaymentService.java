package com.sadeghi.billpayment.service;

import com.sadeghi.billpayment.entity.Payment;
import com.sadeghi.billpayment.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 8:23 PM
 */

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class PaymentService {

    final PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> findByBillIdAndPayId(String billId, String payId) {
        return paymentRepository.findByBillIdAndPayId(billId, payId);
    }

}
