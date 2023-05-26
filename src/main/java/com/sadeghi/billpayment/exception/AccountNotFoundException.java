package com.sadeghi.billpayment.exception;

/**
 * @author Ali Sadeghi
 * Created at 5/25/23 - 11:01 PM
 */
public class AccountNotFoundException extends BusinessException {

    public AccountNotFoundException() {
        super(ExceptionCodes.ACCOUNT_NOT_FOUND_EXCEPTION);
    }

}
