package top.yinxiaokang.framework.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.yinxiaokang.framework.mybatis.simple.dao.UserMapper;
import top.yinxiaokang.framework.mybatis.simple.model.User;

import java.util.Arrays;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectById("1");
        System.out.println(Arrays.toString(users.toArray()));
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(Arrays.toString(users.toArray()));

    }
}
