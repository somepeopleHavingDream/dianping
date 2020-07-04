package org.yangxin.dianping.common;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangxin
 * 2020/07/04 16:44
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) {
//        return e instanceof BusinessException
//                ? CommonResponse.create(((BusinessException) e).getCommonError(), "fail")
//                : CommonResponse.create(new CommonError(BusinessErrorEnum.UNKNOWN_ERROR), "fail");
        if (e instanceof BusinessException) {
            return CommonResponse.create(((BusinessException) e).getCommonError(), "fail");
        } else if (e instanceof NoHandlerFoundException) {
            CommonError commonError = new CommonError(BusinessErrorEnum.NO_HANDLER_FOUND);
            return CommonResponse.create(commonError, "fail");
        } else if (e instanceof ServletRequestBindingException) {
            CommonError commonError = new CommonError(BusinessErrorEnum.BIND_EXCEPTION_ERROR);
            return CommonResponse.create(commonError, "fail");
        } else {
            CommonError commonError = new CommonError(BusinessErrorEnum.UNKNOWN_ERROR);
            return CommonResponse.create(commonError, "fail");
        }
    }
}
