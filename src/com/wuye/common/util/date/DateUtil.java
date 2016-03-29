package com.wuye.common.util.date;

import com.wuye.common.dao.Constants;
import com.wuye.common.util.string.StrUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * =============================================================<br>
 * 版权： 物业软件 版权所有 (c) 2002 - 2003<br>
 * 文件： com.wuye.common.date<br>
 * 所含类： DateUtil<br>
 * 编写人员：tanyw<br>
 * 创建日期：2004-11-04<br>
 * 功能说明：时间指的均为应用服务器时间，而不是数据库服务器时间<br>
 * 更新记录：<br>
 * 日期 作者 内容<br>
 * =============================================================<br>
 * 2004-11-04 tanyw 实现日期的基本功能<br>
 * 2004-10-25 tanyw 增加注释，改方法为静态方法
 * <p/>
 * =============================================================<br>
 */
public class DateUtil {
    // StrUtil su = new StrUtil();

    private static Log log = LogFactory.getLog(DateUtil.class);

    private static String defaultDatePattern = null;

    private static String timePattern = "HH:mm";

    /**
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public DateUtil() {
    }

    /**
     * 将日期格式转化为字符串格式
     *
     * @param date 日期
     * @return String 日期格式的字符串，如"2004-10-25"
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-4 tanyw 创建方法，并实现功能
     * <p/>
     * ==============================================================<br>
     */
    public static String dateToStr(Date date) {
        String ymd = "";

        StringBuffer backstr = new StringBuffer();
        if (date == null) {
            backstr.append("");
        } else {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = time.format(date);
            backstr.append(dateStr);
            /*ymd = DateFormat.getDateInstance().format(date);
            if (ymd != null && ymd.charAt(1) != '-') {
                String[] ymdArr = StrUtil.split(ymd, "-");
                if (ymdArr.length >= 2) {
                    String y = ymdArr[0];
                    String m = ymdArr[1];
                    if (m.length() == 1) {
                        m = "0".concat(m);
                    }
                    String d = ymdArr[2];
                    if (d.length() == 1) {
                        d = "0".concat(d);
                    }
                    backstr.append(y).append("-").append(m).append("-").append(
                            d);
                } else {
                    backstr.append("");
                }
            } else {
                backstr.append("");
            }*/
        }
        return backstr.toString();
    }

    /**
     * 获取YYYYMMDD格式的日期字符串
     *
     * @param date
     * @return
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:May 12, 2008 yejb 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getYYYYMMDD(Date date) {
        String ymd = "";

        StringBuffer backstr = new StringBuffer();

        if (date == null) {
            date = new Date();
        }

        if (date == null) {
            backstr.append("");
        } else {
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
            String dateStr = time.format(date);
            backstr.append(dateStr);
            /*ymd = DateFormat.getDateInstance().format(date);
            if (ymd != null && ymd.charAt(1) != '-') {
                String[] ymdArr = StrUtil.split(ymd, "-");
                if (ymdArr.length >= 2) {
                    String y = ymdArr[0];
                    String m = ymdArr[1];
                    if (m.length() == 1) {
                        m = "0".concat(m);
                    }
                    String d = ymdArr[2];
                    if (d.length() == 1) {
                        d = "0".concat(d);
                    }
                    backstr.append(y).append("").append(m).append("").append(d);
                } else {
                    backstr.append("");
                }
            } else {
                backstr.append("");
            }*/
        }
        return backstr.toString();
    }

    /**
     * 将日期字符串转化为日期格式
     *
     * @param strn 默认日期格式的字符串，如"2004-10-25"
     * @return Date
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-4 tanyw 创建方法，并实现功能
     * <p/>
     * ==============================================================<br>
     */
    public static Date strToDate(String strn) {
        Date returnValue = null;

        if (strn != null && !strn.equals("")) {
            DateFormat df = DateFormat.getDateInstance();
            try {
                returnValue = df.parse(strn);
            } catch (ParseException pe) {
            }
        }
        return returnValue;
    }

    /**
     * 将日期格式转化为具有[日期时间]的字符串格式
     *
     * @param date 日期
     * @return String 日期格式的字符串，如"2004-10-25 14:16:02"
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 wuq 创建方法，并实现功能
     * <p/>
     * ==============================================================<br>
     */

    public static String dateTimeToStr(Date date) {
        String lpDateStr, lpTimeStr;
        lpDateStr = getShortDateStr(date);
        lpTimeStr = getShortTimeStr(date);

        return lpDateStr + " " + lpTimeStr;
    }

    /**
     * 将[日期时间]字符串转化为具有[日期时间]的日期格式
     *
     * @param strn 日期格式的字符串，如"2004-10-25 14:16:02"
     * @return Date 日期
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 wuq 创建方法，并实现功能
     * <p/>
     * ==============================================================<br>
     */

    public static Date strToDateTime(String strn) {
        if (strn != null && strn.length() == 8) {
            String temp = strn.substring(0, 4);
            temp += "-";
            temp += strn.substring(4, 6);
            temp += "-";
            temp += strn.substring(6, 8);
            strn = temp;
        }
        if (strn != null && strn.length() == 10) {
            strn = strn + " 00:00:00";
        }
        Date returnValue = null;
        if (strn != null) {
            DateFormat df = DateFormat.getDateTimeInstance();
            try {
                returnValue = df.parse(strn);
            } catch (ParseException pe) {
            }
        }
        return returnValue;
    }

    /**
     * 返回经计算修改后的时间， 举例：如现在时间为2004-10-14 那么dateAdd("d",2,new
     * Date())返回的时间为2004-10-16 2004-11-4 tanyw 修正月份相减时(12月份、月份天数不一致时)问题<br>
     *
     * @param timeinterval 时间单位(年，月，日..)，取值分别为("y","m","d")
     * @param number       时间间隔值
     * @param sd           时间始点
     * @return Date 日期
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-04 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static Date dateAdd(String timeinterval, int number, Date sd) {
        Date returnDate = null;
        int lpYear = 0, lpMonth = 0, lpDay = 0;
        // int myMonth;
        // int ret;
        String dateStr, lpStrDate = "";
        Date lpsd = null, lped = null;

        lpYear = Integer.parseInt(getYear(dateToStr(sd)));
        lpMonth = Integer.parseInt(getMonth(dateToStr(sd)));
        lpDay = Integer.parseInt(getDay(dateToStr(sd)));

        if (timeinterval.toLowerCase().equals("y")) {
            lpYear += number;
        } else if (timeinterval.toLowerCase().equals("m")) {
            if (number == 0) {
                return sd;
            }
            lpMonth += number;
            int internal = 0;
            if (number > 0) {
                lpStrDate = StrUtil.strnull(lpYear) + "-"
                        + StrUtil.strnull(lpMonth) + "-" + "1";
                lpsd = strToDate(lpStrDate);
                lpStrDate = StrUtil.strnull(lpYear) + "-"
                        + StrUtil.strnull(lpMonth + 1) + "-" + "1";
                lped = strToDate(lpStrDate);
                internal = dateDiff("d", lpsd, lped);
            } else if (number < 0) {
                lpStrDate = StrUtil.strnull(lpYear) + "-"
                        + StrUtil.strnull(lpMonth) + "-" + "1";
                lpsd = strToDate(lpStrDate);
                lpStrDate = getYear(sd) + "-" + getMonth(sd) + "-" + "1";
                lped = strToDate(lpStrDate);
                internal = dateDiff("d", lpsd, lped);
            }
            if (lpDay > internal) {
                lpDay = internal;
            }
        } else if (timeinterval.toLowerCase().equals("d")) {
            lpDay += number;
        }
        dateStr = String.valueOf(lpYear) + "-" + String.valueOf(lpMonth) + "-"
                + String.valueOf(lpDay);
        returnDate = strToDate(dateStr);
        return returnDate;
    }

    /**
     * 返回经计算修改后的时间，举例：如现在时间为2004-10-14 00:00:00 那么dateAdd("h",2,new Date())<br>
     * 返回的时间为2004-10-14 02:00:00
     *
     * @param timeinterval 时间单位(年，月，日，时，分，秒..)，取值分别为("y","m","d","h","f","s")
     * @param number       时间间隔值
     * @param sd           时间始点
     * @return Date
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-4 yejb 创建方法，并实现其功能
     * ==============================================================<br>
     */

    public static Date dateTimeAdd(String timeinterval, int number, Date sd) {
        Date returnDate = null;
        int lpYear = 0, lpMonth = 0, lpDay = 0, lpHour = 0, lpMinutes = 0, lpSeconds = 0;
        String dateStr, lpStrDate = "";
        Date lpsd = null, lped = null;

        lpYear = Integer.parseInt(getYear(dateToStr(sd)));
        lpMonth = Integer.parseInt(getMonth(dateToStr(sd)));
        lpDay = Integer.parseInt(getDay(dateToStr(sd)));
        lpHour = Integer.parseInt(getHour(dateTimeToStr(sd)));
        lpMinutes = Integer.parseInt(getMinutes(dateTimeToStr(sd)));
        lpSeconds = Integer.parseInt(getSeconds(dateTimeToStr(sd)));

        if (timeinterval.toLowerCase().equals("y")) {
            lpYear += number;
        } else if (timeinterval.toLowerCase().equals("m")) {
            if (number == 0) {
                return sd;
            }
            lpMonth += number;
            int internal = 0;
            if (number > 0) {
                lpStrDate = StrUtil.strnull(lpYear) + "-"
                        + StrUtil.strnull(lpMonth) + "-" + "1";
                lpsd = strToDate(lpStrDate);
                lpStrDate = StrUtil.strnull(lpYear) + "-"
                        + StrUtil.strnull(lpMonth + 1) + "-" + "1";
                lped = strToDate(lpStrDate);
                internal = dateDiff("d", lpsd, lped);
            } else if (number < 0) {
                lpStrDate = StrUtil.strnull(lpYear) + "-"
                        + StrUtil.strnull(lpMonth) + "-" + "1";
                lpsd = strToDate(lpStrDate);
                lpStrDate = getYear(sd) + "-" + getMonth(sd) + "-" + "1";
                lped = strToDate(lpStrDate);
                internal = dateDiff("d", lpsd, lped);
            }
            if (lpDay > internal) {
                lpDay = internal;
            }
        } else if (timeinterval.toLowerCase().equals("d")) {
            lpDay += number;
        } else if (timeinterval.toLowerCase().equals("h")) {
            lpHour += number;
        } else if (timeinterval.toLowerCase().equals("f")) {
            lpMinutes += number;
        } else if (timeinterval.toLowerCase().equals("s")) {
            lpSeconds += number;
        }
        dateStr = String.valueOf(lpYear) + "-" + String.valueOf(lpMonth) + "-"
                + String.valueOf(lpDay) + " " + String.valueOf(lpHour) + ":"
                + String.valueOf(lpMinutes) + ":" + String.valueOf(lpSeconds);
        returnDate = strToDateTime(dateStr);
        return returnDate;
    }

    /**
     * 返回两个日期之间的时间间隔
     *
     * @param timeinterval String
     * @param sd           Date 原始日期
     * @param ed           Date 目标日期
     * @return int 返回目标日期减去原始日期的值，如果sd为"2004-10-10 8:00:00",ed为"2004-10-9
     * 23:00:00" timeinterval="d",返回为0,就是间隔的天数为0
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现功能
     * <p/>
     * ==============================================================<br>
     */
    public static int dateDiff(String timeinterval, Date sd, Date ed) {
        int returnValue = 0;

        if (timeinterval.toLowerCase().equals("m")) {
            int lpyear = Integer.parseInt(getYear(dateToStr(ed)))
                    - Integer.parseInt(getYear(dateToStr(sd)));
            if (lpyear >= 0) {
                int ret = lpyear * 12;
                int edmomth = ret + Integer.parseInt(getMonth(dateToStr(ed)));
                returnValue = edmomth
                        - Integer.parseInt(getMonth(dateToStr(sd)));
            }
        } else if (timeinterval.toLowerCase().equals("d")) {
            returnValue = Integer.parseInt(String.valueOf((ed.getTime() - sd
                    .getTime())
                    / (3600 * 24 * 1000)));
        } else if (timeinterval.toLowerCase().equals("s")) {// added by panchh
            returnValue = Integer.parseInt(String.valueOf((ed.getTime() - sd
                    .getTime()) / 1000));
        }
        return returnValue;
    }

    public static String dateDiffToString(Date sd, Date ed) {
        String returnStr = "";

        int iHour = 0;
        int iMinute = 0;

        iHour = Integer.parseInt(String.valueOf((ed.getTime() - sd.getTime())
                / (1000 * 3600)));

        iMinute = Integer
                .parseInt(String.valueOf(((ed.getTime() - sd.getTime())
                        / (1000 * 60) - iHour * 60)));

        returnStr = Math.abs(iHour) + "小时" + Math.abs(iMinute) + "分";

        return returnStr;
    }

    /**
     * 计算两个时间的时间差
     *
     * @param toDate   String 格式如“2004-10-14”
     * @param fromDate String 格式如“2004-10-14”
     * @return int 差的天数 如果toDate为"2004-10-10 8:00:00",fromDate为"2004-10-9
     * 23:00:00" 返回为1,就是间隔的天数为1
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现功能
     * <p/>
     * ==============================================================<br>
     */
    public static int dateDiffDays(String toDate, String fromDate) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        GregorianCalendar g1, g2;
        g1 = new GregorianCalendar(Integer.parseInt(toDate.substring(0, 4)),
                Integer.parseInt(toDate.substring(5, 7)) - 1, Integer
                .parseInt(toDate.substring(8)));
        g2 = new GregorianCalendar(Integer.parseInt(fromDate.substring(0, 4)),
                Integer.parseInt(fromDate.substring(5, 7)) - 1, Integer
                .parseInt(fromDate.substring(8)));

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 计算两个时间的时间差
     *
     * @param toDate   String 格式如“2004-10-14”
     * @param fromDate String 格式如“2004-10-14”
     * @return int 差的月份数
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static int dateDiffMonths(String toDate, String fromDate) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        GregorianCalendar g1, g2;
        g1 = new GregorianCalendar(Integer.parseInt(toDate.substring(0, 4)),
                Integer.parseInt(toDate.substring(5, 7)) - 1, Integer
                .parseInt(toDate.substring(8)));
        g2 = new GregorianCalendar(Integer.parseInt(fromDate.substring(0, 4)),
                Integer.parseInt(fromDate.substring(5, 7)) - 1, Integer
                .parseInt(fromDate.substring(8)));

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc1.clear(Calendar.DATE);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        gc2.clear(Calendar.DATE);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.MONTH, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 取得当前日期
     *
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getYMD() {
        Date myDate = new Date();
        return dateToStr(myDate);
    }

    /**
     * 取得当前日期
     *
     * @return Date
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static Date getNowDate() {

        return new Date();
    }

    /**
     * 取得当前日期
     *
     * @return Timestamp
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static java.sql.Timestamp getNowSqlDateTime() {
        long nCurrentTime = System.currentTimeMillis();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(nCurrentTime);
        return sqlTimestamp;
    }

    /**
     * 取得当前数据库使用的日期
     *
     * @return Date
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static java.sql.Date getNowSqlDate() {
        long nCurrentTime = System.currentTimeMillis();
        java.util.Date utilDate = new java.util.Date(nCurrentTime);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getDate());
        return sqlDate;
    }

    /**
     * 取得当前日期
     *
     * @return Time
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static java.sql.Time getNowSqlTime() {
        long nCurrentTime = System.currentTimeMillis();
        java.util.Date utilDate = new java.util.Date(nCurrentTime);
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        return sqlTime;
    }

    /**
     * 取得当前日期
     *
     * @return Date
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static Date getNewDate() {
        return new Date();
    }

    /**
     * 使用短日期格式显示日期,举例：当前时间是2004-9-1 DateFormat.getDateInstance().format(new
     * Date())返回"2004-9-1" 而getShortDateStr(new Date())返回"2004-09-01"
     *
     * @param date Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getShortDateStr(Date date) {
        String ymd = "";

        StringBuffer backstr = new StringBuffer();
        if (date == null) {
            backstr.append("");
        } else {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = time.format(date);
            backstr.append(dateStr);
            /*ymd = DateFormat.getDateInstance().format(date);
            if (ymd != null && ymd.charAt(1) != '-') {
                String[] ymdArr = StrUtil.split(ymd, "-");
                if (ymdArr.length >= 2) {
                    String y = ymdArr[0];
                    String m = ymdArr[1];
                    if (m.length() == 1) {
                        m = "0".concat(m);
                    }
                    String d = ymdArr[2];
                    if (d.length() == 1) {
                        d = "0".concat(d);
                    }
                    backstr.append(y).append("-").append(m).append("-").append(
                            d);
                } else {
                    backstr.append("");
                }
            } else {
                backstr.append("");
            }*/
        }
        return backstr.toString();
    }

    /**
     * 使用短时间格式显示时间,举例： 当前时间是8:09:08(早上8点9分5秒)
     * DateFormat.getTimeInstance().format(new Date())返回"8:09:08"
     * 而getShortTimeStr(new Date())返回"08:09:05"
     *
     * @param date 日期
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getShortTimeStr(Date date) {
        StringBuffer backstr = new StringBuffer();

        if (date == null) {
            backstr.append("");
        } else {
            String hms = DateFormat.getTimeInstance().format(date);
            String[] hmsArr = StrUtil.split(hms, ":");
            if (hmsArr != null && hmsArr.length > 0 && hmsArr.length == 3) {
                String h = hmsArr[0];
                String m = hmsArr[1];
                String s = hmsArr[2];
                if (h.length() == 1) {
                    h = "0".concat(h);
                }
                if (m.length() == 1) {
                    m = "0".concat(m);
                }
                if (s.length() == 1) {
                    s = "0".concat(s);
                }
                backstr.append(h).append(":").append(m).append(":").append(s);
            }
        }
        return backstr.toString();
    }

    /**
     * 对于指定的年、月、日，返回日期格式的字符串
     *
     * @param lpYear  String
     * @param lpMonth String
     * @param lpDay   String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String dateStrSerial(String lpYear, String lpMonth,
                                       String lpDay) {
        String lpDateStr = "";

        if (!StrUtil.strnull(lpYear).equals("")
                && !StrUtil.strnull(lpMonth).equals("")
                && !StrUtil.strnull(lpDay).equals("")) {
            if (lpMonth.length() == 1) {
                lpMonth = "0" + lpMonth;
            }
            if (lpDay.length() == 1) {
                lpDay = "0" + lpDay;
            }
            lpDateStr = lpYear + "-" + lpMonth + "-" + lpDay;
        }
        return lpDateStr;
    }

    /**
     * 对于指定的年、月、日，返回Date子类型的日期
     *
     * @param lpYear  String
     * @param lpMonth String
     * @param lpDay   String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期::2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */

    public static Date dateSerial(String lpYear, String lpMonth, String lpDay) {
        String lpDateStr = "";
        lpDateStr = dateStrSerial(lpYear, lpMonth, lpDay);
        return strToDate(lpDateStr);
    }

    /**
     * @param date Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */

    public static String getYear(Date date) {
        String ymd = "";
        StringBuffer backstr = new StringBuffer();

        if (date == null) {
            backstr.append("");
        } else {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            ymd = time.format(date);
            //ymd = DateFormat.getDateInstance().format(date);
            if (ymd != null && ymd.charAt(1) != '-') {
                String[] ymdArr = StrUtil.split(ymd, "-");
                if (ymdArr.length >= 2) {
                    String y = ymdArr[0];
                    backstr.append(y);
                } else {
                    backstr.append("");
                }
            } else {
                backstr.append("");
            }
        }
        return backstr.toString();
    }

    /**
     * 根据字符串型入参，取得日期的年份值
     *
     * @param ymd 日期字符串如“2004-05-01”
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getYear(String ymd) {
        StringBuffer backstr = new StringBuffer();
        if (ymd != null && ymd.charAt(1) != '-' && ymd.length() >= 4) {
            String y = ymd.substring(0, 4);
            backstr.append(y);
        } else {
            backstr.append("");
        }
        return backstr.toString();
    }

    /**
     * 根据日期型入参，取得日期的月份值，如果日期为9月，返回为"9"，而不是"09",其他小于9月的月份同理
     *
     * @param date Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getMonth(Date date) {
        String ymd = "";
        StringBuffer backstr = new StringBuffer();
        if (date == null) {
            backstr.append("");
        } else {
            //ymd = DateFormat.getDateInstance().format(date);
            SimpleDateFormat time = new SimpleDateFormat("yyyy-M-d");
            ymd = time.format(date);
            if (ymd != null && ymd.charAt(1) != '-') {
                String[] ymdArr = StrUtil.split(ymd, "-");
                if (ymdArr.length >= 2) {
                    String m = ymdArr[1];
                    backstr.append(m);
                } else {
                    backstr.append("");
                }
            } else {
                backstr.append("");
            }
        }
        return backstr.toString();
    }

    /**
     * 根据字符串型入参，取得日期的月份值,如果日期为9月，返回为"09"，而不是"9",其他小于9月的月份同理
     *
     * @param ymd 日期字符串如“2004-05-01”
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getMonth(String ymd) {
        StringBuffer backstr = new StringBuffer();

        if (ymd != null && ymd.charAt(1) != '-' && ymd.length() >= 6) {
            String m = ymd.substring(5, 7);
            backstr.append(m);
        } else {
            backstr.append("");
        }
        return backstr.toString();
    }

    /**
     * 根据日期型入参，取得日期的日值，如果日期为9号，返回为"9"，而不是"09",其他小于9号的日值同理
     *
     * @param date Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getDay(Date date) {
        String ymd = "";
        StringBuffer backstr = new StringBuffer();

        if (date == null) {
            backstr.append("");
        } else {
            //ymd = DateFormat.getDateInstance().format(date);
            SimpleDateFormat time = new SimpleDateFormat("yyyy-M-d");
            ymd = time.format(date);
            if (ymd != null && ymd.charAt(1) != '-') {
                String[] ymdArr = StrUtil.split(ymd, "-");
                if (ymdArr.length >= 2) {
                    String d = ymdArr[2];
                    backstr.append(d);
                } else {
                    backstr.append("");
                }
            } else {
                backstr.append("");
            }
        }
        return backstr.toString();
    }

    /**
     * 根据字符串入参，取得日期的日值，如果日期为9号，返回为"09"，而不是"9",其他小于9号的日值同理
     *
     * @param ymd 日期字符串如“2004-05-01”
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getDay(String ymd) {
        StringBuffer backstr = new StringBuffer();
        if (ymd != null && ymd.charAt(1) != '-' && ymd.length() >= 10) {
            String d = ymd.substring(8, 10);
            backstr.append(d);
        } else {
            backstr.append("");
        }
        return backstr.toString();
    }

    /**
     * 根据字符串入参,取得小时的值
     *
     * @param str yyyy-mm-dd hh24:mi:ss 如:"2008-08-08 08:08:08"
     * @return String
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-4 yejb 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getHour(String str) {
        StringBuffer backstr = new StringBuffer();
        if (str != null && str.charAt(1) != '-' && str.length() >= 13) {
            String d = str.substring(11, 13);
            backstr.append(d);
        } else {
            backstr.append("");
        }
        return backstr.toString();
    }

    /**
     * 根据字符串入参,取得秒的值
     *
     * @param str yyyy-mm-dd hh24:mi:ss 如:"2008-08-08 08:08:08"
     * @return String
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-4 yejb 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getSeconds(String str) {
        StringBuffer backstr = new StringBuffer();
        if (str != null && str.charAt(1) != '-' && str.length() >= 19) {
            String d = str.substring(17, 19);
            backstr.append(d);
        } else {
            backstr.append("");
        }
        return backstr.toString();
    }

    /**
     * 根据字符串入参,取得分的值
     *
     * @param str String
     * @return String
     * @author: yejb
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-4 yejb 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getMinutes(String str) {
        StringBuffer backstr = new StringBuffer();
        if (str != null && str.charAt(1) != '-' && str.length() >= 16) {
            String d = str.substring(14, 16);
            backstr.append(d);
        } else {
            backstr.append("");
        }
        return backstr.toString();
    }

    /**
     * 返回代表一星期中某天的整数
     *
     * @param date Date
     * @return 返回"0,1,2,3,4,5,6"分别代表Sun,Mon,.....
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static int weekDay(Date date) {
        int ret = 0;
        String strDate, subStr;

        strDate = date.toString();
        subStr = strDate.substring(0, 3);
        if (subStr.equals("Sun")) {
            ret = 0;
        } else if (subStr.equals("Mon")) {
            ret = 1;
        } else if (subStr.equals("Tue")) {
            ret = 2;
        } else if (subStr.equals("Wed")) {
            ret = 3;
        } else if (subStr.equals("Thu")) {
            ret = 4;
        } else if (subStr.equals("Fri")) {
            ret = 5;
        } else if (subStr.equals("Sat")) {
            ret = 6;
        }
        return ret;
    }

    /**
     * 取得星期中指定的某一天的中文名
     *
     * @param date Date
     * @return String 返回一个字符串
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String weekDayName(Date date) {
        String lpReturn = "";
        String strDate, subStr;

        strDate = date.toString();
        subStr = strDate.substring(0, 3);
        if (subStr.equals("Sun")) {
            lpReturn = "星期日";
        } else if (subStr.equals("Mon")) {
            lpReturn = "星期一";
        } else if (subStr.equals("Tue")) {
            lpReturn = "星期二";
        } else if (subStr.equals("Wed")) {
            lpReturn = "星期三";
        } else if (subStr.equals("Thu")) {
            lpReturn = "星期四";
        } else if (subStr.equals("Fri")) {
            lpReturn = "星期五";
        } else if (subStr.equals("Sat")) {
            lpReturn = "星期六";
        }
        return lpReturn;
    }

    /**
     * 取得星期中指定的某一天的中文名
     *
     * @param ymd String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-10-08 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String weekDayName(String ymd) {
        Date lpDate;
        lpDate = strToDate(ymd);
        return weekDayName(lpDate);
    }

    /**
     * 将标准的时间字符串转化为定制的时间字符串(20:04:12 --> 200412)
     *
     * @param lpTimeStr String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-02-23 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getStandardTimeToCustomTimeStr(String lpTimeStr) {
        String lpReturnValue = "";
        if (!StrUtil.strnull(lpTimeStr).equals("")) {
            String[] timeArr = StrUtil.split(lpTimeStr, ":");
            if (timeArr != null && timeArr.length == 3) {
                String lpHour = timeArr[0];
                if (lpHour.length() == 1) {
                    lpHour = "0" + lpHour;
                }
                String lpMinute = timeArr[1];
                if (lpMinute.length() == 1) {
                    lpMinute = "0" + lpMinute;
                }
                String lpSecond = timeArr[2];
                if (lpSecond.length() == 1) {
                    lpSecond = "0" + lpSecond;
                }
                lpReturnValue = lpHour + lpMinute + lpSecond;
            }
        }
        return lpReturnValue;
    }

    /**
     * 将定制的时间字符串转化为标准的时间字符串(200412 --> 20:04:12)
     *
     * @param lpTimeStr 定制的时间串
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-02-23 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getCustomTimeToStandardTimeStr(String lpTimeStr) {
        String lpReturnValue = "";
        if (!StrUtil.strnull(lpTimeStr).equals("") && lpTimeStr.length() == 6) {
            lpReturnValue = lpTimeStr.substring(0, 2) + ":"
                    + lpTimeStr.substring(2, 4) + ":"
                    + lpTimeStr.substring(4, 6);
        }
        return lpReturnValue;
    }

    /**
     * 将标准的日期字符串转化为定制的日期字符串(如:2004-10-16 --> 20031016 )
     *
     * @param strYMD String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getStandardDateStrToCustomDateStr(String strYMD) {
        String lpReturnValue = "";

        if (strYMD == null) {
            lpReturnValue = "";
        } else {
            String[] ymdArr = StrUtil.split(strYMD, "-");
            if (ymdArr != null && ymdArr.length == 3) {
                String lpYear = ymdArr[0];
                String lpMonth = ymdArr[1];
                if (lpMonth.length() == 1) {
                    lpMonth = "0" + lpMonth;
                }
                String lpDay = ymdArr[2];
                if (lpDay.length() == 1) {
                    lpDay = "0" + lpDay;
                }
                lpReturnValue = lpYear + lpMonth + lpDay;
            } else {
                lpReturnValue = strYMD;
            }
        }
        return lpReturnValue;
    }

    /**
     * 将个性的日期字符串转化为标准的日期字符串(如:20031016 --> 2004-10-16)
     *
     * @param strYMD String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String geCustomDateStrtToStandardDateStr(String strYMD) {
        String lpReturnValue = "";

        if (strYMD == null) {
            lpReturnValue = "";
        } else {
            if (strYMD.length() == 8) {
                lpReturnValue = strYMD.substring(0, 4) + "-"
                        + strYMD.substring(4, 6) + "-" + strYMD.substring(6, 8);
            } else {
                lpReturnValue = strYMD;
            }
        }
        return lpReturnValue;
    }

    /**
     * 将一个java.util.Date转化为java.sql.Date
     *
     * @param aDate Date
     * @return Date
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static java.sql.Date util2sql(java.util.Date aDate) {
        if (aDate == null) {
            return null;
        }
        return new java.sql.Date(aDate.getTime());
    }

    /**
     * 将一个java.sql.Date转化为一个java.util.Date
     *
     * @param aDate Date
     * @return Date
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static java.util.Date sql2util(java.sql.Date aDate) {
        if (aDate == null) {
            return null;
        }
        return new java.util.Date(aDate.getTime());
    }

    /**
     * 静态方法：根据参数 ordate 取得 ogdate 所在月份的第一个日期； 如：getMonthFirstDate("200308") =
     * "20030801"。
     *
     * @param ogdate 日期的字符形式，必须是六位（年月）或者八位（年月日）数字； 如："200308"（年月）或者
     *               20030804（年月日）。
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getMonthFirstDate(String ogdate) {
        if (ogdate.length() == 6) {
            ogdate = ogdate + "01";
        } else {
            // 把ogdate变成前6位加01的串，如："20030805" --> "20030801"
            ogdate = ogdate.substring(0, 6) + "01";
        }

        return ogdate;
    }

    /**
     * 静态方法：根据参数 ordate 取得 ogdate 所在月份的最后一个日期； 如：getMonthLastDate("200308") =
     * "20030831"。
     *
     * @param ogdate 日期的字符形式，必须是六位（年月）或者八位（年月日）数字； 如："200308"（年月）或者
     *               20030804（年月日）。
     * @return String 日期的字符（八位数字）形式，如："20030831"。
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getMonthLastDate(String ogdate) {
        if (ogdate.length() == 6) {
            ogdate = ogdate + "01";
        } else {
            // 把ogdate变成前6位加01的串，如："20030805" --> "20030801"
            ogdate = ogdate.substring(0, 6) + "01";
        }
        ogdate = getNextDateByMonth(ogdate, 1);
        ogdate = getNextDateByNum(ogdate, -1);

        return ogdate;
    }

    /**
     * 静态方法：得到在参数起始日期 s 上加 i 天以后的日期（八位数字字符形式）； 如：getNextDateByNum("20030805", 4) =
     * "20030809"。
     *
     * @param s 起始日期，字符形式（八位数字），如："20030805"。
     * @param i 天数（整型），i 可以为负数。
     * @return 日期字符形式（八位数字），如："20030809"。
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getNextDateByNum(String s, int i) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, i);
        date = calendar.getTime();
        s = simpledateformat.format(date);
        return s;
    }

    /**
     * 静态方法：得到在参数起始日期 s 上加 i 月以后的日期（八位数字字符形式）； 如：getNextDateByNum("20030805", 2) =
     * "20031005"。
     *
     * @param s 起始日期，字符形式（八位数字），如："20030805"。
     * @param i 月份数（整型），i 可以为负数。
     * @return 日期字符形式（八位数字），如："20031005"。
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */

    public static String getNextDateByMonth(String s, int i) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, i);
        date = calendar.getTime();
        s = simpledateformat.format(date);
        return s;
    }

    /**
     * 取得当前定制的日期字符串(如：年月日表示 20040430)
     *
     * @return String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getNowCustomDate() {
        String timeStr = "";
        timeStr = getStandardDateStrToCustomDateStr(getYMD());
        return timeStr;
    }

    /**
     * 取得当前的时间字符串(如：时分秒表示 12:00:12 )
     *
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getNowCustomTime() {
        String timeStr = "";
        timeStr = getShortTimeStr(getNowDate());
        return timeStr;
    }

    /**
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
                    locale).getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "yyyy-MM-dd";
        }

        return defaultDatePattern;
    }

    /**
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss";
    }

    /**
     * @param aDate String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * @param aMask   String
     * @param strDate String
     * @return Date
     * @throws ParseException ParseException
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * @param theTime Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * @return Calendar
     * @throws ParseException ParseException
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * @return Timestamp
     * @throws ParseException ParseException
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static Timestamp getCurTime() {
        return new Timestamp((new Date()).getTime());
    }

    /**
     * @param aMask String
     * @param aDate String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * @param aDate Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * @param strDate String
     * @return Date
     * @throws ParseException ParseException
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static Date convertStringToDate(String strDate)
            throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + getDatePattern());
            }

            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                    + "' to a date, throwing exception");
            log.error(pe.getStackTrace());
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());

        }

        return aDate;
    }

    /**
     * @param str String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String strFormatToFull(String str) {
        return dateTimeToStr(strToDateTime(str));
    }

    /**
     * 计算除了周67的工作日
     *
     * @param d1 Date
     * @param d2 Date
     * @return int
     * @author: panchh
     * @修改记录： ==============================================================<br>
     * 日期:2007-8-27 panchh 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static int workDays(Date d1, Date d2) {
        d1 = DateUtil.strToDate(DateUtil.getDate(d1));
        d2 = DateUtil.strToDate(DateUtil.getDate(d2));
        Date d = (d1.before(d2) ? d1 : d2);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int count = 0;
        while (!d.after(d2)) {
            if (d.compareTo(d2) == 0) {
                break;
            }
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day < 7 && day > 1) {
                count++;
            }
            cal.add(Calendar.DATE, 1);
            d = cal.getTime();
            d = DateUtil.strToDate(DateUtil.getDate(d));
        }
        return count;
    }

    /**
     * 当前时间增加几个小时的日期，不包括周六，周日
     *
     * @param intervalHour Integer
     * @return Date
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:Mar 31, 2008 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String workToDate(int intervalHour) {
        int h = intervalHour;
        Date d1 = DateUtil.getNowDate();
        int h1 = 24 - d1.getHours();
        int h2 = h - h1;
        int countDay1 = 0; // 没有工作的天数
        int d3 = h2 / 24;
        int d4 = d3;
        int h3 = h2 % 24;
        int day = 0;
        Calendar cal = Calendar.getInstance();
        int day0 = cal.get(Calendar.DAY_OF_WEEK);
        // cal.setTime(d1);
        if (day0 == 7 || day0 == 1) { //周末受理的单算是周一零点受理的 crm00008001
            if (day0 == 7) { // 如果当天是周六加两天
                cal.add(Calendar.DATE, 2);
            } else if (day0 == 1) {// 如果当天是周日加一天
                cal.add(Calendar.DATE, 1);
            }
            DateFormat df = DateFormat.getDateTimeInstance();
            SimpleDateFormat simpledateformat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            String calDateStr = simpledateformat.format(cal.getTime()) + " 00:00:00";
            try {
                d1 = df.parse(calDateStr);

            } catch (ParseException pe) {
                pe.printStackTrace();
            }

        } else {
            d1 = cal.getTime();
        }
        if (h2 > 0) {
            Date d2 = (Date) d1.clone();
            while (d3 > 0) {
                cal.add(Calendar.DATE, 1); // 取第二天
                day = cal.get(Calendar.DAY_OF_WEEK);
                if (day == 7 || day == 1) { // 周末或假期
                    countDay1++; // 没上班的天数
                } else {
                    d3 = d3 - 1;
                }
            }
            cal.setTime(d2);
            cal.add(Calendar.HOUR, 24 * (d4 + countDay1) + h1 + h3);

        } else {
            cal.add(Calendar.HOUR, h);
        }
        day = cal.get(Calendar.DAY_OF_WEEK);
        while (day == 7 || day == 1) { // 如果是周末或假日要延续
            cal.add(Calendar.DATE, 1); // 取第二天
            day = cal.get(Calendar.DAY_OF_WEEK);
        }
        DateFormat df = DateFormat.getDateTimeInstance();
        String d = df.format(cal.getTime());
        return d;
    }

    public static String getDateAndTime(Date date) {
        String ymd = "";

        StringBuffer backstr = new StringBuffer();
        if (date == null) {
            backstr.append("");
        } else {
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
            String dateStr = time.format(date);
            backstr.append(dateStr);

            String hms = DateFormat.getTimeInstance().format(date);
            String[] hmsArr = StrUtil.split(hms, ":");
            if (hmsArr != null && hmsArr.length > 0 && hmsArr.length == 3) {
                String h = hmsArr[0];
                String m = hmsArr[1];
                String s = hmsArr[2];
                if (h.length() == 1) {
                    h = "0".concat(h);
                }
                if (m.length() == 1) {
                    m = "0".concat(m);
                }
                if (s.length() == 1) {
                    s = "0".concat(s);
                }
                backstr.append(h).append(m).append(s);
            }
        }
        return backstr.toString();
    }

    public static void main(String args[]) {
        DateUtil test = new DateUtil();
        String ss = workToDate(25);
        // System.out.println(ss);
        //Date date = new Date();
        Date nowDate = DateUtil.getNowDate();
        String date = DateUtil.getDateTime("yyyyMMddHHMMSS", nowDate);
        System.out.println(date);

        String date2 = DateUtil.getDateAndTime(new Date());
        System.out.println(date2);

    }

    /**
     * 计算包含的工作日天数
     *
     * @param d1 Date
     * @param d2 Date
     * @return int 返回在开始日期和结束日期之间包含的工作日天数(排除周67不足一个工作日的以0天记)
     * @author: chenjp
     * @修改记录： ==============================================================<br>
     * 日期:2008-5-25 chenjp 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static int dateDiffweekDay(Date d1, Date d2) {
        int count = 0;
        count = Integer.parseInt(String.valueOf((d2.getTime() - d1.getTime())
                / (1000 * 3600 * 24)));
        d1 = DateUtil.strToDate(DateUtil.getDate(d1));
        d2 = DateUtil.strToDate(DateUtil.getDate(d2));
        Date d = (d1.before(d2) ? d1 : d2);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        while (!d.after(d2)) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day == 7 || day == 1) {
                count = count - 1;
            }
            cal.add(Calendar.DATE, 1);
            d = cal.getTime();
            d = DateUtil.strToDate(DateUtil.getDate(d));
        }
        if (count < 0) {
            count = 0;
        }
        return count;
    }

    public static java.sql.Date strToSqlDate(String str) {
        java.sql.Date returnValue = null;
        if (str != null && !str.equals("")) {
            returnValue = new java.sql.Date(DateUtil.strToDate(str).getDate());
        }
        return returnValue;
    }

    /**
     * @param str
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return
     * @author: zfz
     * @修改记录： ==============================================================<br>
     * 日期:Aug 30, 2008      zfz         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static java.util.Date str2date(String str, String format) {
        java.util.Date ret = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            ret = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            ret = null;
        }
        return ret;
    }

    /**
     * @param datetime
     * @return
     * @author: peilh
     * @修改记录： ==============================================================<br>
     * 日期:Jan 21, 2009      peilh         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String datetime2customizestr(Date date) {
        String str = "";
        String datetime = "";
        if (date != null) {
            datetime = dateTimeToStr(date);
        } else {
            datetime = dateTimeToStr(getNowDate());
        }
        if (datetime != null && datetime.length() == 19) {
            str = datetime.substring(0, 4) + datetime.substring(5, 7) + datetime.substring(8, 10) + datetime.substring(11, 13) + datetime.substring(14, 16) + datetime.substring(17, 19);
        }
        return str;
    }

    /**
     * @param number
     * @param sd
     * @param startTime
     * @param endTime
     * @return
     * @author: Administrator
     * @修改记录： ==============================================================<br>
     * 日期:2009-7-29      Administrator         创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static Date dateTimeAddIgnoreStartEnd(int number, Date sd, String startTime, String endTime) {
        Date returnDate = null;
        int lpYear = 0, lpMonth = 0, lpDay = 0, lpHour = 0, lpMinutes = 0, lpSeconds = 0, ignoreMinutes = 0, tempMinutes = 0;
        String dateStr;
        lpYear = Integer.parseInt(getYear(dateToStr(sd)));
        lpMonth = Integer.parseInt(getMonth(dateToStr(sd)));
        lpDay = Integer.parseInt(getDay(dateToStr(sd)));
        lpHour = Integer.parseInt(getHour(dateTimeToStr(sd)));
        lpMinutes = Integer.parseInt(getMinutes(dateTimeToStr(sd)));
        lpSeconds = Integer.parseInt(getSeconds(dateTimeToStr(sd)));
        String todayTime = DateUtil.dateTimeToStr(sd);
        String startDate = todayTime.substring(0, 11).concat(startTime);
        String endDate = todayTime.substring(0, 11).concat(endTime);
        //结束点比开始点小且当前小时大于结束点，则跨天
        if (endTime.compareTo(startTime) < 0 && lpHour > Integer.parseInt(getHour(endDate))) {
            endDate = DateUtil.dateTimeToStr(dateTimeAdd("d", 1, strToDateTime(todayTime.substring(0, 11).concat(endTime))));
        }
        //开始日期大于结束日期，则开始日期要减一
        if (startDate.compareTo(endDate) > 0) {
            startDate = DateUtil.dateTimeToStr(dateTimeAdd("d", -1, strToDateTime(startDate)));
        }
        //String overTime = dateTimeToStr(dateTimeAdd("f", number, sd));
        ////在开始结束时间段内，则需跳过
        if (startDate.compareTo(todayTime) < 0 && endDate.compareTo(todayTime) > 0) {
            lpYear = Integer.parseInt(getYear(endDate));
            lpMonth = Integer.parseInt(getMonth(endDate));
            lpDay = Integer.parseInt(getDay(endDate));
            lpHour = Integer.parseInt(getHour(endDate));
            lpMinutes = Integer.parseInt(getMinutes(endDate));
            lpSeconds = Integer.parseInt(getSeconds(endDate));
        } else if (startDate.compareTo(todayTime) > 0) {
            tempMinutes = dateDiff("s", strToDateTime(todayTime), strToDateTime(startDate)) / 60;
            ignoreMinutes = dateDiff("s", strToDateTime(startDate), strToDateTime(endDate)) / 60;
        }
        lpMinutes += (number - tempMinutes) + ignoreMinutes;

        dateStr = String.valueOf(lpYear) + "-" + String.valueOf(lpMonth) + "-"
                + String.valueOf(lpDay) + " " + String.valueOf(lpHour) + ":"
                + String.valueOf(lpMinutes) + ":" + String.valueOf(lpSeconds);

        returnDate = strToDateTime(dateStr);
        return returnDate;
    }

    /**
     * @param date
     * @param format
     * @return
     * @author: chenhu
     * @修改记录： ==============================================================<br>
     * 日期:Jan 23, 2011     chenhu        创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static String formatToString(java.util.Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                java.text.DateFormat df = new java.text.SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param aDate String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-04-30 tanyw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static final String getDateTimeStr(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDateTimePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
}
