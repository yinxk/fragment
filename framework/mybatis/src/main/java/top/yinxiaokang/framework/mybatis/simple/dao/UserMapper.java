package top.yinxiaokang.framework.mybatis.simple.dao;

import top.yinxiaokang.framework.mybatis.simple.model.User;

import java.util.List;

public interface UserMapper {
    List<User> selectById(String id);
}