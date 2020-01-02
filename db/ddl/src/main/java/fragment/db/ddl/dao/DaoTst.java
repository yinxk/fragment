package fragment.db.ddl.dao;

import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DaoTst {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void test1() {
        
        jdbcTemplate.execute((ConnectionCallback<Object>) con -> {
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet tables = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            PreparedStatement preparedStatement = con.prepareStatement("select * from seq_sequence");
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            Method[] methods = ResultSetMetaData.class.getDeclaredMethods();
            for (Method method : methods) {
                System.out.printf("log.info(\"%s: {}\", metaData.%s(i));\n", method.getName(), method.getName());
            }
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                log.info("isReadOnly: {}", metaData.isReadOnly(i));
                log.info("getScale: {}", metaData.getScale(i));
                log.info("isSigned: {}", metaData.isSigned(i));
                log.info("isCaseSensitive: {}", metaData.isCaseSensitive(i));
                log.info("isWritable: {}", metaData.isWritable(i));
                log.info("isNullable: {}", metaData.isNullable(i));
                log.info("getTableName: {}", metaData.getTableName(i));
                log.info("isAutoIncrement: {}", metaData.isAutoIncrement(i));
                log.info("getColumnLabel: {}", metaData.getColumnLabel(i));
                log.info("getColumnType: {}", metaData.getColumnType(i));
                log.info("getColumnDisplaySize: {}", metaData.getColumnDisplaySize(i));
                log.info("getPrecision: {}", metaData.getPrecision(i));
                log.info("getCatalogName: {}", metaData.getCatalogName(i));
                log.info("getColumnClassName: {}", metaData.getColumnClassName(i));
                log.info("getColumnName: {}", metaData.getColumnName(i));
                log.info("getColumnTypeName: {}", metaData.getColumnTypeName(i));
                log.info("getSchemaName: {}", metaData.getSchemaName(i));
                log.info("isCurrency: {}", metaData.isCurrency(i));
                log.info("isDefinitelyWritable: {}", metaData.isDefinitelyWritable(i));
                log.info("isSearchable: {}", metaData.isSearchable(i));
                log.info("===========");
            }
            return null;
        });
        
    }
}
