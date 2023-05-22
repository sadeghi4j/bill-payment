package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/22/23 - 9:01 PM
 */
public class BillAlreadyPaidException extends BusinessException {

    public BillAlreadyPaidException() {
        super(ExceptionCodes.BILL_ALREADY_PAID_EXCEPTION);
    }

}
