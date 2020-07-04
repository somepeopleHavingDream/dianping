package org.yangxin.dianping.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2020/07/04 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonError {

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误描述
     */
    private String errorMessage;

    public CommonError(BusinessErrorEnum businessErrorEnum) {
        this.errorCode = businessErrorEnum.getErrorCode();
        this.errorMessage = businessErrorEnum.getErrorMessage();
    }
}
