package top.yinxiaokang.util.csv;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单封装一下common csv
 */
public class CSVUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(CSVUtil.class);


    public static void toCSV(List<String> keyList, List<String> headerList, List<Object> valueList, Appendable out) {
        // todo keyList is null or headerList is null or valueList is null
        String[] headers = new String[headerList.size()];
        headerList.toArray(headers);
        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);
        try {
            CSVPrinter printer = new CSVPrinter(out, format);
            printer.printRecord(valueList);
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("", e);
            }
            throw new RuntimeException(e);
        }

    }
}
