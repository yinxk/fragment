package top.yinxiaokang.framework.mybatis.simple.mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.yinxiaokang.framework.mybatis.simple.dao.UserMapper;
import top.yinxiaokang.framework.mybatis.simple.model.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = mapper.selectById("1");
            System.out.println(Arrays.toString(users.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = mapper.selectAll();
            System.out.println(Arrays.toString(users.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testInsert() {
        try (SqlSession session = getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("test");
            user.setUserPassword("test");
            user.setUserEmail("test@xx.com");
            user.setCreateTime(new Date());
            user.setUserInfo("ddd");
            user.setHeadImg(new byte[]{1,2,3,4,5,5});
            user.setTestColumn("dddd");


            int insert = mapper.insert(user);
            System.out.println(insert);
        }
    }
}
