package interfaces;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface BaseExcelWriter<T> {
    void process(Workbook wb, Map<String, String> keyMap, List<Map<String, T>> content);
}