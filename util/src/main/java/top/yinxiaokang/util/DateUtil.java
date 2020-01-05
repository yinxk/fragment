package top.yinxiaokang.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Java8中时间API与Date相互转换
 *
 * @author yinxk
 */
@SuppressWarnings("WeakerAccess")
public class DateUtil {
    /**
     * 常用格式1
     * yyyy-MM-dd HH:mm:ss 格式
     */
    public final static DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * 常用格式2
     * yyyy-MM-dd
     */
    public final static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Date转换为LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate转换为Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转换为LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转化为Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转换为LocalTime
     */
    public static LocalTime date2LocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * LocalTime转换为Date(其中LocalDate为系统当前日期)
     */
    public static Date localTime2Date(LocalTime localTime) {
        return localDateTime2Date(LocalDateTime.of(LocalDate.now(), localTime));
    }
}