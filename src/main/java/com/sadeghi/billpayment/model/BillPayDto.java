package com.sadeghi.billpayment.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 10:04 PM
 */

@Schema(description = "مدل ورودی پرداخت قبض")
public record BillPayDto(
        @Schema(description = "شناسه قبض", minLength = 13, maxLength = 13) @NotNull String billId,
        @Schema(description = "شناسه پرداخت", minLength = 13, maxLength = 13) @NotNull String payId
) {
}
