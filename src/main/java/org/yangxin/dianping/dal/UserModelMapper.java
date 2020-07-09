package org.yangxin.dianping.dal;

import org.apache.ibatis.annotations.Param;
import org.yangxin.dianping.model.UserModel;

public interface UserModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Integer id);

    UserModel selectByTelephonePassword(@Param("telephone") String telephone, @Param("password") String password);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}