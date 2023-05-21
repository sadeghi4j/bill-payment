package com.sadeghi.billpayment.service;

import com.sadeghi.billpayment.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

}
