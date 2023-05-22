package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/22/23 - 9:10 PM
 */
public class BillPaymentException extends BusinessException {
    public BillPaymentException() {
        super(ExceptionCodes.BILL_PAYMENT_EXCEPTION);
    }
}
