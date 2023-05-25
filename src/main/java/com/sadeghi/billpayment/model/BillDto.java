package com.sadeghi.billpayment.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 9:59 PM
 */
@Schema(description = "مدل متناظر موجودیت قبض")
public record BillDto(@Schema(description = "شناسه قبض", minLength = 13, maxLength = 13) String billId,
                      @Schema(description = "شناسه پرداخت", minLength = 13, maxLength = 13) String payId,
                      @Schema(description = "نوع قبض") BillType billType,
                      @Schema(description = "شماره تلفن") String phoneNumber,
                      @Schema(description = "مبلغ قبض") Integer amount) {
}
