package org.yangxin.dianping.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2020/07/04 16:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    /**
     * 表明请求的返回处理结果，“success”或“fail”
     */
    private String status;

    /**
     * 若status=success时，表明对应的返回的json类数据
     * 若status=fail时，则data内将使用通用的错误码对应的格式
     */
    private Object data;

    /**
     * 定义一个通用的创建返回对象的方法
     */
    public static CommonResponse create(Object result) {
        return create(result, "success");
    }

    public static CommonResponse create(Object result, String status) {
//        return CommonResponse.builder()
//                .status(status)
//                .data(result)
//                .build();
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status);
        commonResponse.setData(result);
        return commonResponse;
    }
}
