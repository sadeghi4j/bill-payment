package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/26/23 - 6:47 PM
 */
public class InsufficientBalanceException extends BusinessException{
    public InsufficientBalanceException() {
        super(ExceptionCodes.ACCOUNT_ID_NOT_SELFIE_EXCEPTION);
    }
}
