package common.SqlHelper;

import common.usingtool.ConfigDataService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateExchange {

    private static String dateForm = ConfigDataService.getValue("dateForm");
    private Date date;
    private Calendar calendar = Calendar.getInstance();
    public static Date staDate = new GregorianCalendar(1900, 0, -1).getTime();//1900-1-1


    public DateExchange() {
        date = new Date();
        calendar.setTime(date);
    }

    public DateExchange(String dateStr) throws Exception {
        date = new SimpleDateFormat(dateForm).parse(dateStr);
        calendar.setTime(date);
    }

    public Date getDate() {
        return this.date;
    }

    /**
     * 获得时间戳
     */
    public String getTimeStamp() {
        return String.valueOf(date.getTime());
    }

    /**
     * 获取时间字符串
     */
    public String getDateStr() {
        return new SimpleDateFormat(dateForm).format(date);
    }

    public String getYear() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }


    public String getMonth() {
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    public String getDay() {
        return String.valueOf(calendar.get(Calendar.DATE));
    }

    public String getHour() {
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }

    public String getMinute() {
        return String.valueOf(calendar.get(Calendar.MINUTE));
    }

    public String getSecond() {
        return String.valueOf(calendar.get(Calendar.SECOND));
    }


    public static Date add(Date date, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            //1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作。
            c.add(5, amount);
            return c.getTime();
        }
    }

    public static String getstaDateStr(Date date) {
        return new SimpleDateFormat(dateForm).format(date);
    }


}
