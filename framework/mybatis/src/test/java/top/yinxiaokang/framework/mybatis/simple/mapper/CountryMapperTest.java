package top.yinxiaokang.framework.mybatis.simple.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import top.yinxiaokang.framework.mybatis.simple.model.Country;

import java.util.List;

@Slf4j
public class CountryMapperTest extends BaseMapperTest{

    private static SqlSessionFactory SQL_SESSION_FACTORY;


    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
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
