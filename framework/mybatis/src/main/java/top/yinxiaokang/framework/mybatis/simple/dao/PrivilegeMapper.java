package top.yinxiaokang.framework.mybatis.simple.dao;

import org.apache.ibatis.annotations.SelectProvider;
import top.yinxiaokang.framework.mybatis.simple.model.Privilege;
import top.yinxiaokang.framework.mybatis.simple.provider.PrivilegeProvider;

public interface PrivilegeMapper {
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    Privilege selectById(final Long id);
}