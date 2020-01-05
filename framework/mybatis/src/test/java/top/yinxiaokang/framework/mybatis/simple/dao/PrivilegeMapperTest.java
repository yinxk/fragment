package top.yinxiaokang.framework.mybatis.simple.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.yinxiaokang.framework.mybatis.simple.mapper.BaseMapperTest;
import top.yinxiaokang.framework.mybatis.simple.model.Privilege;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void selectById() {
        try(SqlSession sqlSession = getSqlSession()){
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            Privilege privilege = mapper.selectById(1L);
            System.out.println(privilege);
        }
    }
}