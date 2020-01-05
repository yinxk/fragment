package top.yinxiaokang.framework.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import top.yinxiaokang.framework.mybatis.simple.model.Country;

@Slf4j
public class CountryMapperTest extends BaseMapperTest{

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
