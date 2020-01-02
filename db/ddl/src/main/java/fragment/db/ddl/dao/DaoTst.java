package fragment.db.ddl.dao;

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
            ResultSetMetaData metaData1 = tables.getMetaData();
            PreparedStatement preparedStatement = con.prepareStatement("select * from seq_sequence");
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                log.info("name : {}", metaData.getColumnName(i));
                log.info("type : {}", metaData.getColumnType(i));
                log.info("scale : {}", metaData.getScale(i));
            }
            return null;
        });
        
    }
}
