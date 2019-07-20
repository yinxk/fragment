package top.yinxiaokang.framework.mybatis.simple.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import top.yinxiaokang.framework.mybatis.simple.model.Country;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Slf4j
public class CounrtyMapperTest {

    private static SqlSessionFactory SQL_SESSION_FACTORY;


    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = SQL_SESSION_FACTORY.openSession()) {
            List<Country> selectAll = sqlSession.selectList("selectAll");
            printCountryList(selectAll);
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            log.info("country: {}", country);
        }
    }
}
