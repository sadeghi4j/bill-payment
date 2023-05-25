package com.sadeghi.billpayment.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Ali Sadeghi
 * Created at 5/18/23 - 8:25 PM
 */
@Schema(description = "مدل خروجی احراز هویت")
public record JwtResponse(@Schema(description = "توکن JWT") String jwtToken) {
}
