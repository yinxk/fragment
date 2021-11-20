package chip.date20211120.first;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TradeDayCalculate {

    public static void main(String[] args) throws ParseException {
        List<String> tradeDayList = Arrays.asList("20160501", "20160627", "20160628",
                "20160701", "20160704", "20160705", "20160708", "20160709");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TradeDayCalculate tradeDayCalculate = new TradeDayCalculate();
        tradeDayCalculate.init(tradeDayList);
        System.out.println(tradeDayCalculate.getTradeDay(dateFormat.parse("2016-05-01 14:20:22"), 3));
    }

    private static final String DAY_FORMAT_PATTERN = "yyyyMMdd";
    private static final String TIME_FORMAT_PATTERN = "HHmmssSSS";
    private static final DateTimeFormatter TRADE_DAY_FORMATTER = DateTimeFormatter.ofPattern(DAY_FORMAT_PATTERN);
    private static final String CLOSE_TIME = "145959999";
    private List<String> tradeDayList;
    private Map<String, Day> dayMap;

    /**
     * 工具初始化，初始化的目的是让工具具备更加合适各的数据结构，方便计算提高效率
     *
     * @param tradeDayList 包含一年内所有的交易日起，格式如：20160701 20160704 20160705，非交易日20160702 20160703不在其中.
     */
    public void init(List<String> tradeDayList) {
        this.tradeDayList = new ArrayList<>(tradeDayList);
        this.dayMap = new LinkedHashMap<>();
        this.tradeDayList.sort(String::compareTo);
        int start = tradeDayList.size() - 1;
        LocalDate cur = null;
        String tradeDay;
        String dayStr;
        for (int i = start; i >= 0; ) {
            tradeDay = tradeDayList.get(i);
            if (i == start) {
                cur = LocalDate.parse(tradeDay, TRADE_DAY_FORMATTER);
            }
            dayStr = cur.format(TRADE_DAY_FORMATTER);
            if (dayStr.equals(tradeDay)) {
                dayMap.put(tradeDay, new Day(i, Boolean.TRUE));
                i--;
            } else {
                dayMap.put(dayStr, new Day(i + 1, Boolean.FALSE));
            }
            cur = cur.minusDays(1);
        }
    }

    /**
     * 给定任意时间，返回给定时间的T+n交易日
     *
     * @param time       给定要计算的时间。
     * @param offsetDays 交易日偏移量，offsetDays可以为负数，表示T-n的计算。
     */
    public String getTradeDay(Date time, int offsetDays) {
        String dayStr = new SimpleDateFormat(DAY_FORMAT_PATTERN).format(time);
        Day day = dayMap.get(dayStr);
        // 这里理解的是交易日历是静态数据, 如果不在日历中, 不进行计算
        if (day == null) {
            throw new RuntimeException("不在交易日历中");
        }
        String timeStr = new SimpleDateFormat(TIME_FORMAT_PATTERN).format(time);
        int nextTradeIndex = day.getNextTradeDayIndex();
        if (timeStr.compareTo(CLOSE_TIME) > 0 && day.getSelfTradeDay()) {
            nextTradeIndex++;
        }
        int tnTradeIndex = nextTradeIndex + offsetDays;
        if (tnTradeIndex < 0 || tnTradeIndex >= tradeDayList.size()) {
            throw new RuntimeException("不在交易日历中");
        }
        return tradeDayList.get(tnTradeIndex);
    }

    private static class Day {

        private Integer nextTradeDayIndex;

        private Boolean selfTradeDay;

        public Day(Integer nextTradeDayIndex, Boolean selfTradeDay) {
            this.nextTradeDayIndex = nextTradeDayIndex;
            this.selfTradeDay = selfTradeDay;
        }

        public Integer getNextTradeDayIndex() {
            return nextTradeDayIndex;
        }

        public void setNextTradeDayIndex(Integer nextTradeDayIndex) {
            this.nextTradeDayIndex = nextTradeDayIndex;
        }

        public Boolean getSelfTradeDay() {
            return selfTradeDay;
        }

        public void setSelfTradeDay(Boolean selfTradeDay) {
            this.selfTradeDay = selfTradeDay;
        }
    }
}
