package top.yinxiaokang.framework.mybatis.simple.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import top.yinxiaokang.framework.mybatis.simple.mapper.BaseMapperTest;
import top.yinxiaokang.framework.mybatis.simple.model.Role;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void selectById() {
        try (SqlSession sqlSession = getSqlSession()) {

            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Role role = mapper.selectById(1L);
            System.out.println(role);
        }
    }
}