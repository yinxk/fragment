package fragment.sequence.util;

import java.math.BigInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fragment.sequence.service.SequenceGenService;

@Component
public class SequenceGeneratorUtil {

    @Autowired
    private SequenceGenService sequenceGenService;

    private static SequenceGenService generator;

    @PostConstruct
    public void init() {
        SequenceGeneratorUtil.generator = sequenceGenService;
    }

    /**
     * 根据序列名获取下一个值
     *
     * @param sequenceName 序列名
     * @return next value
     */
    public static BigInteger nextVal(String sequenceName) {
        return generator.nextVal(sequenceName);
    }
}
