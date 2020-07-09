package org.yangxin.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yangxin
 * 2020/07/09 14:17
 */
@Data
public class LoginRequest {

    @NotBlank(message = "手机号不能为空")
    private String telephone;

    @NotBlank(message = "密码不能为空")
    private String password;
}
