package com.sadeghi.billpayment.controller;

import com.sadeghi.billpayment.entity.Bill;
import com.sadeghi.billpayment.exception.BillNotFoundException;
import com.sadeghi.billpayment.mapper.DTOMapper;
import com.sadeghi.billpayment.model.BillDto;
import com.sadeghi.billpayment.model.BillType;
import com.sadeghi.billpayment.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 9:57 PM
 */

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class BillPaymentController {

    final BillService billService;

    @GetMapping(value = "/bill/inquiry/{billType}/{identifier}")
    public BillDto billInquiry(@PathVariable BillType billType, @PathVariable String identifier) {
        Optional<Bill> optionalBill = billService.findBill(billType, identifier);
        return optionalBill.map(DTOMapper.INSTANCE::convert).orElseThrow(BillNotFoundException::new);
    }

}
