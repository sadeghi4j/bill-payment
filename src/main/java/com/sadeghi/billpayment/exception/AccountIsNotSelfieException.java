package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/25/23 - 11:08 PM
 */
public class AccountIsNotSelfieException extends BusinessException {
    public AccountIsNotSelfieException() {
        super(ExceptionCodes.ACCOUNT_ID_NOT_SELFIE_EXCEPTION);
    }
}
