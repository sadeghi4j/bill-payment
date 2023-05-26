package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/26/23 - 9:50 PM
 */
public class InProgressPaymentException extends BusinessException{

    public InProgressPaymentException() {
        super(ExceptionCodes.IN_PROGRESS_PAYMENT_EXCEPTION);
    }

}
