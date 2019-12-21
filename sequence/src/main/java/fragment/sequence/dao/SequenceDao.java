package fragment.sequence.dao;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import fragment.sequence.model.SequenceModel;


@Repository
public class SequenceDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 查询所有的序列
     *
     * @return 所有序列
     */
    public List<SequenceModel> findAll() {
        String sql = " SELECT " +
                "           sequence_name," +
                "           min_value," +
                "           max_value," +
                "           cycle_flag," +
                "           last_number," +
                "           segment_size" +
                "    FROM" +
                "        seq_sequence";
        return namedParameterJdbcTemplate.query(sql, new SequenceRowMapper());
    }

    /**
     * 根据序列名查询序列
     *
     * @param sequenceName 序列名
     * @return 序列对象
     */
    public SequenceModel findBySequenceName(String sequenceName) {
        String sql = " SELECT " +
                "           sequence_name," +
                "           min_value," +
                "           max_value," +
                "           cycle_flag," +
                "           last_number," +
                "           segment_size" +
                "    FROM" +
                "        seq_sequence" +
                "    WHERE" +
                "       sequence_name = :sequenceName";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sequenceName", sequenceName);
        List<SequenceModel> query = namedParameterJdbcTemplate.query(sql, paramMap, new SequenceRowMapper());
        if (CollectionUtils.isEmpty(query)) {
            return null;
        }
        return query.get(0);
    }

    /**
     * 根据序列名和旧值更新新值
     *
     * @param sequenceName 序列名
     * @param oldValue     旧值
     * @param newValue     新值
     * @return 更新行数
     */
    public int updateSequenceByNameAndOldValue(String sequenceName, BigInteger oldValue, BigInteger newValue) {

        String sql = "  UPDATE seq_sequence" +
                "       SET last_number = :newValue" +
                "       WHERE sequence_name = :sequenceName" +
                "               AND last_number = :oldValue";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sequenceName", sequenceName);
        paramMap.put("oldValue", oldValue);
        paramMap.put("newValue", newValue);
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    private static class SequenceRowMapper implements RowMapper<SequenceModel> {
        @Override
        public SequenceModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            SequenceModel sequenceModel = new SequenceModel();
            sequenceModel.setSequenceName(rs.getString("sequence_name"));
            sequenceModel.setMinValue(rs.getBigDecimal("min_value").toBigInteger());
            sequenceModel.setMaxValue(rs.getBigDecimal("max_value").toBigInteger());
            sequenceModel.setCycleFlag(rs.getInt("cycle_flag") > 0);
            sequenceModel.setLastNumber(rs.getBigDecimal("last_number").toBigInteger());
            sequenceModel.setSegmentSize(rs.getBigDecimal("segment_size").toBigInteger());
            return sequenceModel;
        }
    }


}
