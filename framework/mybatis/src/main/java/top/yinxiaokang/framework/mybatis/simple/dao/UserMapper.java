package top.yinxiaokang.framework.mybatis.simple.dao;

import top.yinxiaokang.framework.mybatis.simple.model.User;

import java.util.List;

public interface UserMapper {
    List<User> selectById(String id);

    List<User> selectAll();

    int insert(User user);

    int insert2(User user);

    int insert3(User user);

    int insert4(User user);


}