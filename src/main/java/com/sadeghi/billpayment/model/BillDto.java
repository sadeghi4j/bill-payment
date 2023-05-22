package com.sadeghi.billpayment.model;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 9:59 PM
 */

public record BillDto(String billId, String payId, BillType billType, String phoneNumber, Integer amount) {
}
