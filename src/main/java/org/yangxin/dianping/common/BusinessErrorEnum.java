package org.yangxin.dianping.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangxin
 * 2020/07/04 16:24
 */
@AllArgsConstructor
@Getter
public enum BusinessErrorEnum {

    /**
     * 通用的错误类型以10000开头
     */
    NO_OBJECT_FOUND(10001, "请求对象不存在"),
    UNKNOWN_ERROR(10002, "未知错误"),
    NO_HANDLER_FOUND(10003, "找不到执行的路径操作"),
    BIND_EXCEPTION_ERROR(10004, "请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10005, "请求参数校验失败"),

    /**
     * 用户服务相关的错误类型20000开头
     */
    REGISTER_DUPLICATE_ERROR(20001, "用户已存在"),
    LOGIN_FAIL(20002, "手机号或密码错误");

    /**
     * 错误代码
     */
    private final Integer errorCode;

    /**
     * 错误描述
     */
    private final String errorMessage;
}
