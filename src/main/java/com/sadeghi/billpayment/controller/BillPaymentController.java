package com.sadeghi.billpayment.controller;

import com.sadeghi.billpayment.entity.Bill;
import com.sadeghi.billpayment.exception.BillNotFoundException;
import com.sadeghi.billpayment.mapper.DTOMapper;
import com.sadeghi.billpayment.model.BillDto;
import com.sadeghi.billpayment.model.BillPayDto;
import com.sadeghi.billpayment.model.BillType;
import com.sadeghi.billpayment.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 9:57 PM
 */
@Tag(name = "Bill Payment Controller", description = "کنترلر استعلام و پرداخت قبض")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class BillPaymentController {

    final BillService billService;

    @Operation(summary = "استعلام قبض توسط شناسه قبض یا شماره تلفن",
            parameters = {
                    @Parameter(name = "billType", description = "نوع قبض", required = true),
                    @Parameter(name = "identifier", description = "شناسه قبض یا شماره تلفن", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "استعلام قبض با موفقیت انجام شد"),
                    @ApiResponse(responseCode = "400", description = "قبض موردنظر یافت نشد"),
                    @ApiResponse(responseCode = "401", description = "عدم دسترسی"),
            }
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @GetMapping(value = "/bill/inquiry/{billType}/{identifier}")
    public BillDto billInquiry(@PathVariable BillType billType, @PathVariable String identifier) {
        Optional<Bill> optionalBill = billService.findBill(billType, identifier);
        return optionalBill.map(DTOMapper.INSTANCE::convert).orElseThrow(BillNotFoundException::new);
    }

    @Operation(summary = "پرداخت قبض بوسیله شناسه قبض و شناسه پرداخت استعلام شده در سرویس استعلام قبض",
            responses = {
                    @ApiResponse(responseCode = "200", description = "پرداخت قبض با موفقیت انجام شد"),
                    @ApiResponse(responseCode = "400", description = "قبض موردنظر یافت نشد | شناسه قبض یا شناسه پرداخت خالی می باشد"),
                    @ApiResponse(responseCode = "401", description = "عدم دسترسی")
            }
    )
    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping(value = "/bill/pay")
    public void billPay(@Valid @RequestBody BillPayDto billPayDto) {
        log.info("payment : {}", billPayDto);
        billService.billPay(billPayDto);
    }

}
