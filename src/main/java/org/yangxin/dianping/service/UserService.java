package org.yangxin.dianping.service;

import org.yangxin.dianping.common.BusinessException;
import org.yangxin.dianping.model.UserModel;

import java.security.NoSuchAlgorithmException;

/**
 * @author yangxin
 * 2020/07/02 20:29
 */
public interface UserService {

    UserModel getUser(Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException;
}
