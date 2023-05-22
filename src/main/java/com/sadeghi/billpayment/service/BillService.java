package com.sadeghi.billpayment.service;

import com.sadeghi.billpayment.entity.Bill;
import com.sadeghi.billpayment.model.BillType;
import com.sadeghi.billpayment.repository.BillRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

    public Optional<Bill> findBillByBillId(String billId) {
        return billRepository.findByBillId(billId);
    }

    public Optional<Bill> findBillByPhoneNumber(String phoneNumber) {
        return billRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Bill> findBill(BillType billType, String identifier) {
        return billType == BillType.MOBILE_PHONE ? findBillByPhoneNumber(identifier) : findBillByBillId(identifier);
    }


}
