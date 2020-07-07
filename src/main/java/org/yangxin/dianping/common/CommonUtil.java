package org.yangxin.dianping.common;

import org.springframework.validation.BindingResult;

/**
 * @author yangxin
 * 2020/07/07 14:24
 */
public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> stringBuilder.append(fieldError.getDefaultMessage()));

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
