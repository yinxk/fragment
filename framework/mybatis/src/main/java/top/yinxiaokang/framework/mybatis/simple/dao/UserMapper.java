package top.yinxiaokang.framework.mybatis.simple.dao;

import top.yinxiaokang.framework.mybatis.simple.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);
}