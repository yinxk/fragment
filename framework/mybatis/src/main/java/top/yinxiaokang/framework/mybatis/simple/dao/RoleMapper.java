package top.yinxiaokang.framework.mybatis.simple.dao;

import org.apache.ibatis.annotations.Select;
import top.yinxiaokang.framework.mybatis.simple.model.Role;

public interface RoleMapper {

    @Select({
            "select * from sys_role where id = #{id}"
    })
    Role selectById(Long id);
}