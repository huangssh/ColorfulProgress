/**
 * @Title: UtilTime.java
 * @Package com.ct.client.common.utils
 * @Description: 工具包
 * @author linwen@ffcs.cn
 * @date 2015年7月30日 上午11:13:13
 * @version V1.0
 */
package com.huangssh.colorfulprogress.Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 时间操作
 *
 * @author linwen@ffcs.cn
 * @ClassName: UtilTime
 * @date 2015年7月30日 上午11:13:13
 */
public class UtilTime {

    /**
     * 获取计费日期
     *
     * @return String    *月1日-*月*日
     * @author zhuofq
     */
    public static String getCountDate() {

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        return month + "月1日-" + month + "月" + day + "日";
    }

    /**
     * 获取带年份的计费日期
     *
     * @return String    *月1日-*月*日 , 月日小于10补0
     */
    public static String getCountNewDate() {

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        String newMonth = month + "";
        String newDay = day + "";
        if (month < 10){
            newMonth = "0" + newMonth;
        }
        if (day < 10){
            newDay = "0" + day;
        }
        String result = year + "年" + newMonth + "月01日-" + newMonth + "月" + newDay + "日";
        return result;
    }

    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyymm");
        String time = format.format(new Date());
        return time;
    }

    public static String getCurrentDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyymm");
        String currentDate = format.format(new Date(date));
        return currentDate;
    }

    /**
     * 获取当前的日期
     *
     * @return String    year年month月day日
     * @author zhuofq
     */
    public static String getDate() {

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 获取当前的天
     */
    public static int getDayOfMonth() {
        Calendar cal = Calendar.getInstance();//使用日历类
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        return day;
    }

    /**
     * 返回过去num个月。 格式 ： yyyymm
     *
     * @param hasThisMonth true-包括当月  false-不包括当月
     * @param num          几个月 （最大值：13）
     * @return
     * @author jiangwenxin
     * @createTime 2014年9月11日 下午4:39:32
     */
    public static List<String> getLast6Month(Boolean hasThisMonth, int num) {
        List<String> months = new ArrayList<String>();

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        int start = hasThisMonth ? 0 : 1;
        for (int i = start; i <= num + start - 1; i++) {
            int mon;
            if (month - i > 0) {
                mon = month - i;
                months.add(year + (mon > 9 ? "" + mon : "0" + mon));
            } else {
                mon = month - i + 12;
                months.add((year - 1) + (mon > 9 ? "" + mon : "0" + mon));
            }
        }
        Collections.reverse(months);
        return months;
    }

    /**
     * 说明：获取标题显示用的月份集合
     *
     * @作者 hongrx
     * @创建时间 2016/3/1 15:05
     * @版本 5.6.0
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */

    public static List<String> getTiteMonthList(List<String> mMonthList, boolean mIsCurrentMonth) {
        List<String> mTitleList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try {
            for (int i = 0; i < mMonthList.size(); i++) {
                String monthTitle;
                if (mIsCurrentMonth && i == mMonthList.size() - 1) monthTitle = "本月";
                else {
                    String month = mMonthList.get(i);
                    Date monthDate = sdf.parse(month);
                    int monthNum = monthDate.getMonth() + 1;
                    if (monthNum < 10) monthTitle = "0" + monthNum + "月";
                    else monthTitle = monthNum + "月";
                }
                mTitleList.add(monthTitle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mTitleList;
    }

    /**
     * 说明：获取标题显示用的月份
     *
     * @作者 hongrx
     * @创建时间 2016/3/1 15:05
     * @版本 5.6.0
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */

    public static String getTiteMonth(String month) {
        String monthTitle = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        List<String> nowMonthList = getLast6Month(true, 1);
        try {
            if (nowMonthList.get(0).equals(month)) monthTitle = "本月";
            else {
                Date monthDate = sdf.parse(month);
                int monthNum = monthDate.getMonth() + 1;
                if (monthNum < 10) monthTitle = "0" + monthNum + "月";
                else monthTitle = monthNum + "月";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monthTitle;
    }


    public static String numTOTiteMonth(String num) {
        String monthTitle = null;
        int numInt = Integer.parseInt(num);
        if (numInt <= 0 || numInt > 12) return "0";
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;//获得当前月数
        if (numInt == month) return "本月";

        if (numInt < 10)
            monthTitle = "0" + num + "月";
        else
            monthTitle = num + "月";
        return monthTitle;
    }


    /**
     * 获取本月剩余的天数,包括今天
     *
     * @return
     * @author zhuofq
     */
    public static int getLeftDays() {
        Calendar cal = Calendar.getInstance();
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int today = cal.get(Calendar.DAY_OF_MONTH);
        int left = days - today + 1;
        if (left < 0 || left > 31) {
            left = 1;
        }
        return left;
    }

    /**
     * 获取本月有多少天
     * @return
     */
    public static int getThisMonHasDays() {
        Calendar cal = Calendar.getInstance();
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 获取问候语
     *
     * @return String
     * @author zhuofq
     */
    public static String getDateGreeting() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 6) {
            return "凌晨好";
        } else if (hour < 9) {
            return "早上好";
        } else if (hour < 12) {
            return "上午好";
        } else if (hour < 14) {
            return "中午好";
        } else if (hour < 17) {
            return "下午好";
        } else if (hour < 19) {
            return "傍晚好";
        } else if (hour < 22) {
            return "晚上好";
        } else {
            return "夜里好";
        }
    }

    private static long mTimeWatch = 0;

    /**
     * 开始时间
     *
     * @author zhuofq
     */
    public static void startTimeWatch() {
        mTimeWatch = System.currentTimeMillis();
    }

    /**
     * 结束时间
     *
     * @return long    毫秒数
     * @author zhuofq
     */
    public static long stopTimeWatch() {
        return System.currentTimeMillis() - mTimeWatch;
    }

    /**
     * 获取时间
     *
     * @return String    YmdHMS
     * @author zhuofq
     */
    public static String getTimestamp() {
        Calendar c = Calendar.getInstance();
        return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", c);
    }

    /**
     * 获取当前日期 格式：M月dd日 HH:mm
     *
     * @return String
     * @author zhuofq
     */
    public static String getDateTime() {
        long ms = System.currentTimeMillis();
        // SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yy");
        SimpleDateFormat df = new SimpleDateFormat("MM月dd日 HH:mm");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    /**
     * 获取当前时间	格式：HH:mm dd/MM/yy
     *
     * @param ms
     * @return String
     * @author zhuofq
     */
    public static String getDateTime(long ms) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yy");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    /**
     * 转换毫秒数	格式：H:M:S
     *
     * @param allsecond
     * @return String
     * @author zhuofq
     */
    public static String getFormatTime(long allsecond) {
        long hour = allsecond / 3600;
        long minute = (allsecond % 3600) / 60;
        long second = allsecond % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    /**
     * 转换毫秒数	格式：yyyy-MM-dd HH:mm:ss
     *
     * @param ms
     * @return String
     * @author zhuofq
     */
    public static String msToDateTime(long ms) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    /**
     * 获取毫秒数
     *
     * @param time
     * @return long
     * @author zhuofq
     */
    public static long getMsFromTimeString(String time) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            return date.getTime();
        } catch (ParseException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    /**
     * Sets the time of this Calendar.
     *
     * @param time
     * @return String
     * @author zhuofq
     */
    public static Calendar getCalFromTimeString(String time) {
        Calendar cal = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            return cal;
        }
    }

    /**
     * 获取时间	格式：YMDHmS
     *
     * @param calendar
     * @return String
     * @author zhuofq
     */
    public static String getTimeString(Calendar calendar) {
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DATE);
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMin = calendar.get(Calendar.MINUTE);
        int mSec = calendar.get(Calendar.SECOND);
        return String.format("%04d%02d%02d%02d%02d%02d", mYear, mMonth + 1,
                mDay, mHour, mMin, mSec);
    }

    /**
     * 获取当年的年月	格式：YM
     *
     * @return String
     * @author zhuofq
     */
    public static String getThisYearMonth() {
        Calendar c = Calendar.getInstance();
        return String.format("%1$tY%1$tm", c);
    }

    /**
     * 获取时间	格式：HH:mm
     *
     * @param ms
     * @return String
     * @author zhuofq
     */
    public static String msToTime(long ms) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    public static String getRechargeTime(String date) {
        String time = "";
        if (date.length()>16){
            String hour = date.substring(11, 13);
            String min = date.substring(14, 16);
            String day = date.substring(8,10);
            time = day + "日" + " " + hour + ":" + min;
        }
        return time;
    }

    /**
     * 获取时间	格式：yyyy-MM-dd
     *
     * @param ms
     * @return String
     * @author zhuofq
     */
    public static String msToDate(long ms) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    public static String monthToDate(String month) {
        String year = "";
        String m = "";
        try {
            year = month.substring(0, 4);
            m = month.substring(4, month.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year + "年" + m + "月";
    }

    public static long getMsFromTime(String time) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(time);
            return date.getTime();
        } catch (ParseException e) {
            // e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获取时间	格式：yyyy年MM月dd日
     *
     * @return String
     * @author hrx
     */
    public static String msToDateTo(long ms) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    /**
     * 获取时间	格式：HH:mm:ss
     *
     * @return String
     * @author hrx
     */
    public static String msToDateHMS(long ms) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Timestamp now = new Timestamp(ms);
        return df.format(now);
    }

    /**
     * 获取时间	格式：HH.mm.ss
     *
     * @return String
     * @author huangssh
     */
    public static String getNowDateShort(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String dateString = formatter.format(strToDateLong(date));
        return dateString;
    }


    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String strToFormatYMDStr(String time) {
        Date startDate = null;
        String newTime = "";
        if (time != null && !"".equals(time)) {
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newTime = new SimpleDateFormat("yyyy.MM.dd").format(startDate);
        }
        return newTime;
    }

    /**
     * 传入long类型的时间戳和格式
     * luy
     * @param pattern
     * @param dateTime
     * @return
     */
    public static String getFormatedDateTime(String pattern, long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }

    /**
     * 获取系统时间
     * @param format 格式
     * @return
     */
    public static String getSystemTime(String format) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = UtilTime.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 获取星期
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = UtilTime.getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 获取周
     * @param sdate
     * @return
     */
    public static String getZhouStr(String sdate) {
        String str = "";
        str = UtilTime.getWeek(sdate);
        if ("1".equals(str)) {
            str = "周日";
        } else if ("2".equals(str)) {
            str = "周一";
        } else if ("3".equals(str)) {
            str = "周二";
        } else if ("4".equals(str)) {
            str = "周三";
        } else if ("5".equals(str)) {
            str = "周四";
        } else if ("6".equals(str)) {
            str = "周五";
        } else if ("7".equals(str)) {
            str = "周六";
        }
        return str;
    }

    public static long formatDate(String dateStr) {
        Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d = new Date(String.valueOf(sf.parse(dateStr)));
        } catch (Exception e) {

        }
        return d.getTime();
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

}
