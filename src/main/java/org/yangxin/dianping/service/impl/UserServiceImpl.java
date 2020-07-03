package org.yangxin.dianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangxin.dianping.dal.UserModelMapper;
import org.yangxin.dianping.model.UserModel;
import org.yangxin.dianping.service.UserService;

/**
 * @author yangxin
 * 2020/07/02 20:47
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserModelMapper userModelMapper;

    @Autowired
    public UserServiceImpl(UserModelMapper userModelMapper) {
        this.userModelMapper = userModelMapper;
    }

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
