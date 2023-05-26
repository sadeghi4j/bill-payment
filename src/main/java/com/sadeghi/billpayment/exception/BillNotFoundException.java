package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/21/23 - 10:15 PM
 */
public class BillNotFoundException extends BusinessException {

    public BillNotFoundException() {
        super(ExceptionCodes.BILL_NOT_FOUND_EXCEPTION);
    }

}
