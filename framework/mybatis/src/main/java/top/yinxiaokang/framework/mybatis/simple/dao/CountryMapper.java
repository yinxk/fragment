package top.yinxiaokang.framework.mybatis.simple.dao;

import top.yinxiaokang.framework.mybatis.simple.model.Country;

import java.util.List;

public interface CountryMapper {
    List<Country> selectAll();
}
