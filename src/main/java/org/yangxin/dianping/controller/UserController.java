package org.yangxin.dianping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yangxin.dianping.common.BusinessErrorEnum;
import org.yangxin.dianping.common.BusinessException;
import org.yangxin.dianping.common.CommonError;
import org.yangxin.dianping.common.CommonResponse;
import org.yangxin.dianping.model.UserModel;
import org.yangxin.dianping.service.UserService;

/**
 * @author yangxin
 * 2020/07/02 20:28
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/index.html");
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonResponse getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
            throw new BusinessException(BusinessErrorEnum.NO_OBJECT_FOUND);
        } else {
            return CommonResponse.create(userModel);
        }
    }
}
