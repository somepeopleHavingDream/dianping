package org.yangxin.dianping.common;

import lombok.Getter;

/**
 * @author yangxin
 * 2020/07/04 16:33
 */
@Getter
public class BusinessException extends Exception {

    private final CommonError commonError;

    public BusinessException(BusinessErrorEnum businessErrorEnum) {
        super();
        this.commonError = new CommonError(businessErrorEnum);
    }
}
