package com.sadeghi.billpayment.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * @author Ali Sadeghi
 * Created at 5/18/23 - 8:12 PM
 */

@Schema(description = "مدل ورودی احراز هویت")
public record JwtRequest(
        @Schema(description = "نام کاربری") @NotNull String username, @Schema(description = "کلمه عبور") @NotNull String password

) {
}