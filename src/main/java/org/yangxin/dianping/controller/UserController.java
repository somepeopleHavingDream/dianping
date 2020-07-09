package org.yangxin.dianping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yangxin.dianping.common.*;
import org.yangxin.dianping.model.UserModel;
import org.yangxin.dianping.request.LoginRequest;
import org.yangxin.dianping.request.RegisterRequest;
import org.yangxin.dianping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/**
 * @author yangxin
 * 2020/07/02 20:28
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    // 这里其实能够直接用HttpSession
    private final HttpServletRequest httpServletRequest;

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    public UserController(UserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
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

    @RequestMapping("/register")
    @ResponseBody
    public CommonResponse register(@Valid @RequestBody RegisterRequest registerRequest,
                                   BindingResult bindingResult)
            throws NoSuchAlgorithmException, BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }

        UserModel registerUser = new UserModel();
        registerUser.setTelphone(registerRequest.getTelephone());
        registerUser.setPassword(registerRequest.getPassword());
        registerUser.setNickName(registerRequest.getNickName());
        registerUser.setGender(registerRequest.getGender());

        UserModel register = userService.register(registerUser);
        return CommonResponse.create(register);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonResponse login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) throws BusinessException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = userService.login(loginRequest.getTelephone(), loginRequest.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);

        return CommonResponse.create(userModel);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonResponse logout() {
        httpServletRequest.getSession().invalidate();
        return CommonResponse.create(null);
    }

    /**
     * 获取当前用户信息
     */
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public CommonResponse getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonResponse.create(userModel);
    }
}
