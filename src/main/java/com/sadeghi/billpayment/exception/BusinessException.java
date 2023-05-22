package com.sadeghi.billpayment.exception;

import lombok.Getter;

/**
 * @author Ali Sadeghi
 * Created at 5/19/23 - 4:59 PM
 */

@Getter
public class BusinessException extends RuntimeException {

    final ExceptionCodes exceptionCode;

    public BusinessException(ExceptionCodes exceptionCode) {
        super();
        this.exceptionCode = exceptionCode;
    }
}
