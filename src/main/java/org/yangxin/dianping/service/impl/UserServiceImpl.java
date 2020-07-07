package org.yangxin.dianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.dianping.common.BusinessErrorEnum;
import org.yangxin.dianping.common.BusinessException;
import org.yangxin.dianping.dal.UserModelMapper;
import org.yangxin.dianping.model.UserModel;
import org.yangxin.dianping.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import static java.util.Base64.*;

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

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, NoSuchAlgorithmException {
        registerUser.setPassword(encodeByMD5(registerUser.getPassword()));
        // 这个地方，数据库并未设置自动时间创建更新
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());

        try {
            userModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessErrorEnum.REGISTER_DUPLICATE_ERROR);
        }

        return getUser(registerUser.getId());
    }

    /**
     * 通过MD5编码
     */
    private String encodeByMD5(String s) throws NoSuchAlgorithmException {
        // 确认计算方法MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        Encoder encoder = getEncoder();
        return Arrays.toString(encoder.encode(messageDigest.digest(s.getBytes(StandardCharsets.UTF_8))));
    }
}
