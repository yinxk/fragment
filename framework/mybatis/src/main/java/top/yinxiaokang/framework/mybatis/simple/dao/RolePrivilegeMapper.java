package top.yinxiaokang.framework.mybatis.simple.dao;

import top.yinxiaokang.framework.mybatis.simple.model.RolePrivilege;

public interface RolePrivilegeMapper {
    int insert(RolePrivilege record);

    int insertSelective(RolePrivilege record);
}