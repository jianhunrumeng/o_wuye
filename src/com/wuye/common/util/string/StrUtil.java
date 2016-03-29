package com.wuye.common.util.string;

import com.wuye.common.exception.ManagerException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.BCodec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;

/**
 * =============================================================<br>
 * 版权： 福富软件 版权所有 (c) 2002 - 2003.<br>
 * 文件： com.ffsc.crm.common.string<br>
 * 所含类： StrUtil<br>
 * 编写人员：tanyw<br>
 * 创建日期：2004-11-04<br>
 * 功能说明：<br>
 * 更新记录：<br>
 * 日期 作者 内容<br>
 * =============================================================<br>
 * 2004-11-04 tanyw 实现字符串及相关转化的基本功能<br>
 * 2004-10-22 tanyw 整理，添加新函数，主要把大多数的函数改成static型<br>
 * =============================================================<br>
 */
public class StrUtil {

    /**
     * Log log。
     */
    private static final Log log = LogFactory.getLog(StrUtil.class);

    static PatternCompiler patterCompiler = new Perl5Compiler();
    static PatternMatcher patterMatcher = new Perl5Matcher();

    /**
     * 构造函数.
     */
    protected StrUtil() {

    }

    /**
     * 将Unicode码字符串转为为GBK码.
     *
     * @param strIn String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String GBToUnicode(final String strIn) {
        String strOut = null;

        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            final byte[] b = strIn.getBytes("GBK");
            strOut = new String(b, "ISO8859_1");
        } catch (final Exception e) {
        }
        return strOut;
    }

    /**
     * 将GBK码转换为Unicode码.
     *
     * @param strIn String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String unicodeToGB(final String strIn) {
        String strOut = null;

        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            final byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        } catch (final Exception e) {
        }
        return strOut;
    }

    /**
     * 字符串编码类型转换.
     *
     * @param str        待转换的字符串
     * @param oldCharset 待转换的字符串的编码类型
     * @param newCharset 新的编码类型
     * @return 转换成新编码类型的字符串
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String encode(final String str, final String oldCharset, final String newCharset) {
        if (str == null) {
            return str;
        }
        String newStr = null;
        try {
            newStr = new String(str.getBytes(oldCharset), newCharset);
        } catch (final Exception e) {
        }
        return newStr;

    }

    /**
     * 将以sgn为分隔符的字符串转化为数组.
     *
     * @param str String
     * @param sgn String
     * @return String[]
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-4 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String[] split(String str, final String sgn) {
        String[] returnValue = null;
        if (!StrUtil.strnull(str).equals("")) {
            final Vector vectors = new Vector();
            int i = str.indexOf(sgn);
            String tempStr = "";
            for (; i >= 0; i = str.indexOf(sgn)) {
                tempStr = str.substring(0, i);
                str = str.substring(i + sgn.length());
                vectors.addElement(tempStr);
            }
            if (!str.equalsIgnoreCase("")) {
                vectors.addElement(str);
            }
            returnValue = new String[vectors.size()];
            for (i = 0; i < vectors.size(); i++) {
                returnValue[i] = (String) vectors.get(i);
                returnValue[i] = returnValue[i].trim();
            }
        }
        return returnValue;
    }

    /**
     * 把数组转化为字符串.
     *
     * @param array 字符串数组
     * @param split 分割字符串
     * @return string whose format is like "1,2,3"
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String arrayToStr(final String[] array, final String split) {
        if (array == null || array.length < 1) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(split);
            }
            sb.append(StrUtil.strnull(array[i]));
        }
        return sb.toString();

    }

    /**
     * @param array String[]
     * @param split String
     * @return string whose format like " '1','2','3'"
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String arrayToStrWithStr(final String[] array, final String split) {
        return StrUtil.arrayToStrWithStr(array, split, "0");

    }

    /**
     * @param array   String[]
     * @param split   String
     * @param optType 操作类型0:普通; 1:在解析角色时去掉
     * @return String string whose format like " '1','2','3'"
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String arrayToStrWithStr(final String[] array, final String split,
                                           final String optType) {
        if (array == null || array.length < 1) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("'");
            if (optType.equals("1")) {
                final String temp = StrUtil.strnull(array[i]);
                sb.append(temp.substring(1, temp.length()));
            } else {
                sb.append(StrUtil.strnull(array[i]));
            }
            sb.append("'");

        }
        return sb.toString();

    }

    /**
     * 将以sgn为分隔符的字符串转化为数组.
     *
     * @param str String
     * @param sgn String
     * @return String[]
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-4 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String[] strConvertoArray(final String str, final String sgn) {
        final StringTokenizer st = new StringTokenizer(str, sgn);
        final String[] retstr = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            retstr[i] = st.nextToken();
        }
        return retstr;
    }

    /**
     * 将以sgn为分隔符的字符串转化为List链表.
     *
     * @param str String 要处理的字符串
     * @param sgn String 间隔符
     * @return List
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2005-03-17 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static List strConvertoList(final String str, final String sgn) {
        final StringTokenizer st = new StringTokenizer(str, sgn);
        final List result = new LinkedList();

        for (int i = 0; st.hasMoreTokens(); i++) {
            result.add(st.nextToken());
        }
        return result;
    }

    /**
     * 1 --> 00001将整数转化为指定长度字符串(lpMaxLength为5).
     *
     * @param lpInt       int
     * @param lpMaxLength int
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String intToStr(final int lpInt, final int lpMaxLength) {
        int length, i;
        String returnValue = "";

        length = Integer.toString(lpInt).length();
        if (length < lpMaxLength) {
            i = lpMaxLength - length;
            while (i > 0) {
                returnValue = returnValue + "0";
                i--;
            }
            returnValue = returnValue + Integer.toString(lpInt);
        } else {
            returnValue = Integer.toString(lpInt);
        }
        return returnValue;
    }

    /**
     * 将字符集转换成整型.
     *
     * @param source String
     * @return int
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static int toInt(final String source) {
        try {
            return Integer.parseInt(source);
        } catch (final NumberFormatException notint) {
            return 0;
        }
    }

    /**
     * 取路径后的文件名，也就是路径字串最后一个斜杠后的字串.
     *
     * @param path String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getPathFile(final String path) {
        String substr = "";
        try {
            if (path != null && !path.equals("")) {
                final int i = path.lastIndexOf("/");
                substr = path.substring(i + 1).trim();
            }
        } catch (final Exception ex) {
            System.err.println(ex);
        }
        return substr;
    }

    /**
     * 取小数点后的字串，也就是小数点后的字串.
     *
     * @param str String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getLastTwo(final String str) {
        String substr = "";
        try {
            if (str != null && !str.equals("")) {
                final int i = str.lastIndexOf(".");
                substr = str.substring(i + 1).trim();
            }
        } catch (final Exception ex) {
            System.err.println(ex);
        }
        return substr;
    }

    /**
     * 取得文件名的文件类型(如2003001.doc-->doc).
     *
     * @param lpFileName String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getFileType(final String lpFileName) {
        String lpReturnValue = "";

        if (lpFileName != null && !lpFileName.equals("")) {
            final int i = lpFileName.lastIndexOf(".");
            lpReturnValue = lpFileName.substring(i, lpFileName.length());
        }
        return lpReturnValue;
    }

    /**
     * 返回位于 String 对象中指定位置的子字符串.
     *
     * @param str        String
     * @param beginIndex int
     * @param endIndex   int
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getSubString(String str, final int beginIndex, final int endIndex) {
        String str1 = "";

        if (str == null) {
            str = "";
        }
        if (str.length() >= beginIndex && str.length() >= endIndex) {
            str1 = str.substring(beginIndex, endIndex);
        } else {
            str1 = str;
        }
        return str1;
    }

    /**
     * 如果入参是null或者"",用另一入参rpt替代入参返回，否则返回入参的trim().
     *
     * @param str String
     * @param rpt String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final String str, final String rpt) {
        if (str == null || str.equals("null") || str.equals("") || str.trim() == null) {
            return rpt;
        } else {
            return str.trim();
        }
    }

    /**
     * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号.
     *
     * @param strn String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final String strn) {
        return StrUtil.strnull(strn, "");
    }

    /**
     * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号.
     *
     * @param str Object
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final Object str) {
        String result = "";
        if (StrUtil.isNullOrEmpty(str)) {
            result = "";
        } else {
            result = str.toString();
        }
        return result;
    }

    /**
     * 适用于web层 为检查null值，如果为null，将返回"&nbsp;"，不为空时将替换其非html符号.
     *
     * @param strn String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String repnull(final String strn) {
        return StrUtil.strnull(strn, "&nbsp;");
    }

    /**
     * 把Date的转化为字符串，为空时将为"0000-00-00 00:00:00".
     *
     * @param strn Date
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final Date strn) {
        String str = "";

        if (strn == null) {
            str = "0000-00-00 00:00:00";
        } else {
            // strn.toGMTString();
            str = strn.toString();
        }
        return (str);
    }

    /**
     * 把BigDecimal的转换为字符串，为空将返回0.
     *
     * @param strn BigDecimal
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final BigDecimal strn) {
        String str = "";

        if (strn == null) {
            str = "0";
        } else {
            str = strn.toString();
        }
        return (str);
    }

    /**
     * 把int的转换为字符串(不为空，只起转换作用).
     *
     * @param strn int
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final int strn) {
        final String str = String.valueOf(strn);
        return (str);
    }

    /**
     * 把float的转换为字符串(不为空，只起转换作用).
     *
     * @param strn float
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final float strn) {
        final String str = String.valueOf(strn);
        return (str);
    }

    /**
     * 把long的转换为字符串(不为空，只起转换作用).
     *
     * @param strn float
     * @return String
     */
    public static String strnull(final long strn) {
        final String str = String.valueOf(strn);
        return (str);
    }

    /**
     * 把double的转换为字符串(不为空，只起转换作用).
     *
     * @param strn double
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strnull(final double strn) {
        final String str = String.valueOf(strn);
        return (str);
    }

    /**
     * 0-15转化为0-F.
     *
     * @param bin int
     * @return char
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static char hex(final int bin) {
        char retval;
        if (bin >= 0 && bin <= 9) {
            retval = (char) ('0' + bin);
        } else if (bin >= 10 && bin <= 15) {
            retval = (char) ('A' + bin - 10);
        } else {
            retval = '0';
        }
        return retval;
    }

    /**
     * 字符串替换.
     *
     * @param content   String
     * @param oldString String
     * @param newString String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2004-1-6 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String replace(final String content, final String oldString,
                                 final String newString) {
        if (content == null || oldString == null) {
            return content;
        }
        if (content.equals("") || oldString.equals("")) {
            return content;
        }

        String resultString = "";
        int stringAtLocal = content.indexOf(oldString);
        int startLocal = 0;
        while (stringAtLocal >= 0) {
            resultString = resultString + content.substring(startLocal, stringAtLocal) + newString;
            startLocal = stringAtLocal + oldString.length();
            stringAtLocal = content.indexOf(oldString, startLocal);
        }

        resultString = resultString + content.substring(startLocal, content.length());
        return resultString;
    }

    /**
     * 替换字符串内容.
     *
     * @param strSource 源字符串
     * @param strFrom   源
     * @param strTo     目标
     * @return String
     * @author: Liuzhuangfei
     * @修改记录： ==============================================================<br>
     * 日期:Feb 9, 2010 Liuzhuangfei 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String replaceStr(String strSource, final String strFrom, final String strTo) {
        if (strFrom == null || strFrom.equals("")) {
            return strSource;
        }
        String strDest = "";
        final int intFromLen = strFrom.length();
        int intPos;
        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
    }

    /**
     * 替换字符串中某几位的值
     *
     * @param source
     * @param format
     * @param from
     * @param to
     * @return String
     * @author：fangj
     * @修改记录：=============================================== 日期：2012-10-18     fangj     创建方法
     * ================================================
     */
    public static String replaceStr(String source, String format, int from, int to) {
        if (source == null || "".equals(source))
            return source;
        if (from > to)
            return source;
        final int sourceLen = source.length();
        if (sourceLen < from)
            return source;
        if (from < 0)
            from = 0;
        if (sourceLen - 1 <= to)
            to = sourceLen - 1;
        String dest = "";
        dest = dest + source.substring(0, from);
        for (int i = 0; i <= (to - from); i++) {
            dest = dest + format;
        }
        dest = dest + source.substring(to + 1, sourceLen);

        return dest;
    }

    /**
     * @param strn String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String formatToHTML(final String strn) {
        final StringBuffer dest = new StringBuffer();
        if (StrUtil.strnull(strn).equals("")) {
            dest.append("&nbsp;");
        } else {
            for (int i = 0; strn != null && i < strn.length(); i++) {
                final char c = strn.charAt(i);
                if (c == '\n') {
                    dest.append("<br>");
                } else if (c == '\'') {
                    dest.append("&#39;");
                } else if (c == '\"') {
                    dest.append("&#34;");
                } else if (c == '<') {
                    dest.append("&lt;");
                } else if (c == '>') {
                    dest.append("&gt;");
                } else if (c == '&') {
                    dest.append("&amp;");
                } else if (c == 0x20) {
                    dest.append("&nbsp;");
                } else {
                    dest.append(c);
                }
            }
        }
        return (dest.toString());
    }

    /**
     * @param strn   String
     * @param length int
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String formatToHTML(final String strn, final int length) {
        int m = 0;
        final StringBuffer dest = new StringBuffer();
        if (StrUtil.strnull(strn).equals("")) {
            dest.append("&nbsp;");
        } else {
            for (int i = 0; strn != null && i < strn.length(); i++) {
                m++;
                if (m == length) {
                    dest.append("...");
                    break;
                }
                final char c = strn.charAt(i);
                if (c == '\n') {
                    dest.append("<br>");
                } else if (c == '\'') {
                    dest.append("&#39;");
                } else if (c == '\"') {
                    dest.append("&#34;");
                } else if (c == '<') {
                    dest.append("&lt;");
                } else if (c == '>') {
                    dest.append("&gt;");
                } else if (c == '&') {
                    dest.append("&amp;");
                } else if (c == 0x20) {
                    dest.append("&nbsp;");
                } else {
                    dest.append(c);
                }
            }
        }
        return (dest.toString());
    }

    /**
     * @param strb BigDecimal
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String formatToHTML(final BigDecimal strb) {
        String strn = "";
        if (strb == null) {
            strn = "&nbsp;";
        } else {
            strn = StrUtil.strnull(strb);
        }
        return strn;
    }

    /**
     * 将多行字符串转为有带有回车、换行的HTML格式.
     *
     * @param source String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String nl2Br(final String source) {
        if (StrUtil.strnull(source).equals("")) {
            return "&nbsp;";
        }
        final StringBuffer dest = new StringBuffer(source.length());
        for (int i = 0; i < source.length(); i++) {
            char c;
            c = source.charAt(i);
            if (c == '\n') {
                dest.append("<br>");
            } else if (c == 0x20) {
                dest.append("&nbsp;");
            } else {
                dest.append(c);
            }
        }
        return dest.toString();
    }

    /**
     * @param sourceStr String
     * @param fieldStr  String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static boolean findString(final String sourceStr, final String fieldStr) {
        boolean strExist = false;
        if (sourceStr.length() == 0) {
            return strExist;
        }
        if (sourceStr.indexOf(fieldStr) >= 0) {
            strExist = true;
        }
        return strExist;
    }

    /**
     * @param exception Throwable
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getStackTrace(final Throwable exception) {
        final StringWriter sw = new StringWriter();
        return sw.toString();
    }

    /**
     * 给字符串数组排序.
     *
     * @param arr String[] 要进行排序的字符串数组
     * @return String[] 排序后的字符串数组
     * @author: Linxf
     * @修改记录： ==============================================================<br>
     * 日期:2004-08-09 Linxf 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String[] bubbleSort(final String[] arr) {
        int tag = 1;
        for (int i = 1; i < arr.length && tag == 1; i++) {
            tag = 0;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    final String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    tag = 1;
                }
            }
        }
        return arr;
    }

    /**
     * 依据ValueArr数组的排序，为ContentArr排序.
     *
     * @param valueArr   String[]
     * @param contentArr String[]
     * @return String[]
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String[] bubbleSort(final String[] valueArr, final String[] contentArr) {
        int tag = 1;
        for (int i = 1; i < valueArr.length && tag == 1; i++) {
            tag = 0;
            for (int j = 0; j < valueArr.length - i; j++) {
                if (valueArr[j].compareTo(valueArr[j + 1]) > 0) {
                    final String temp1 = valueArr[j];
                    final String temp2 = contentArr[j];
                    valueArr[j] = valueArr[j + 1];
                    contentArr[j] = contentArr[j + 1];
                    valueArr[j + 1] = temp1;
                    contentArr[j + 1] = temp2;
                    tag = 1;
                }
            }
        }
        return valueArr;
    }

    /**
     * 冒泡排序.
     *
     * @param arr int[] 要进行排序的数组
     * @return int[] 排序后的数组
     * @author: Linxf
     * @修改记录： ==============================================================<br>
     * 日期:2004-08-09 Linxf 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static int[] bubbleSort(final int[] arr) {
        int tag = 1;
        for (int i = 1; i < arr.length && tag == 1; i++) {
            tag = 0;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    final int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    tag = 1;
                }
            }
        }
        return arr;
    }

    /**
     * 将空字符串转为"0"字符串.
     *
     * @param str String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String nullToZero(String str) {
        if (str == null || str.equals("") || "null".equals(str)) {
            str = "0";
        }
        return str;
    }

    /**
     * request中获取long类型的参数值.
     *
     * @param request  HttpServletRequest
     * @param paraName String
     * @return long
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static long getlongParameter(final HttpServletRequest request, final String paraName) {
        final long value = new Long(StrUtil.nullToZero(request.getParameter(paraName))).longValue();
        return value;
    }

    /**
     * 从request中获取long类型的参数值.
     *
     * @param request  HttpServletRequest
     * @param paraName String
     * @return Long
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static Long getLongParameter(final HttpServletRequest request, final String paraName) {
        final Long value = new Long(StrUtil.nullToZero(request.getParameter(paraName)));

        return value;
    }

    /**
     * 从request中获取int类型的参数值.
     *
     * @param request  HttpServletRequest
     * @param paraName String
     * @return int
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static int getIntParameter(final HttpServletRequest request, final String paraName) {
        final int value = Integer.parseInt(StrUtil.nullToZero(request.getParameter(paraName)));
        return value;
    }

    /**
     * 从request中获取String类型的参数值.
     *
     * @param request  HttpServletRequest
     * @param paraName String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getStringParameter(final HttpServletRequest request, final String paraName) {
        final String value = StrUtil.strnull(request.getParameter(paraName));
        return value;
    }

    /**
     * 返回字段的PO名.
     *
     * @param obName String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getPOFieldName(final String obName) {
        String aFieldName = obName;
        if (aFieldName == null) {
            return null;
        }
        aFieldName = aFieldName.toLowerCase();
        while (aFieldName.indexOf("_") >= 0) {
            if (aFieldName.indexOf("_") >= 0) {
                final int pos = aFieldName.indexOf("_");
                final String low = aFieldName.substring(0, pos);
                final String midd = aFieldName.substring(pos + 1, pos + 2).toUpperCase();
                final String end = aFieldName.substring(pos + 2, aFieldName.length());
                aFieldName = low + midd + end;
            }
        } // end while
        return aFieldName;
    }

    /**
     * 返回表的PO名.
     *
     * @param obName String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getPOTableName(final String obName) {
        String aTableName = obName;
        if (aTableName == null) {
            return null;
        }
        aTableName = aTableName.toLowerCase();
        while (aTableName.indexOf("_") >= 0) {
            if (aTableName.indexOf("_") >= 0) {
                final int pos = aTableName.indexOf("_");
                final String low = aTableName.substring(0, pos);
                final String midd = aTableName.substring(pos + 1, pos + 2).toUpperCase();
                final String end = aTableName.substring(pos + 2, aTableName.length());
                aTableName = low + midd + end;
            }
        } // end while
        aTableName = aTableName.substring(0, 1).toUpperCase()
                + aTableName.substring(1, aTableName.length());
        return aTableName;
    }

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials string
     * is returned
     *
     * @param password  Password or other credentials to use in authenticating this
     *                  username
     * @param algorithm Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(final String password, final String algorithm) {
        final byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (final Exception e) {
            StrUtil.log.error("Exception: " + e);

            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        final byte[] encodedPassword = md.digest();

        final StringBuffer buf = new StringBuffer();

        for (final byte element : encodedPassword) {
            if ((element & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(element & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * @param str String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String encodeString(final String str) {
        String ret = str;
        try {
            final BCodec bc = new BCodec();
            ret = StrUtil.strnull(bc.encode(str));
        } catch (EncoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str String
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String decodeString(final String str) {
        final BCodec bc = new BCodec();
        try {
            return StrUtil.strnull(bc.decode(str));
        } catch (final Exception ex) {
            return null;
        }
    }

    /**
     * 在右边填充字符串.
     *
     * @param rString 要填充的初始字符串
     * @param rLength 填充后的长度
     * @param rPad    填充字符
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String padTrailing(final String rString, final int rLength, final String rPad) {
        String lTmpPad = "";

        final String lTmpStr = StrUtil.strnull(rString);

        if (lTmpStr.length() >= rLength) {
            return lTmpStr.substring(0, lTmpStr.length());
        } else {
            for (int gCnt = 1; gCnt <= rLength - lTmpStr.length(); gCnt++) {
                lTmpPad = rPad + lTmpPad;
            }
        }
        return lTmpStr + lTmpPad;
    }

    /**
     * 在左边填充字符串.
     *
     * @param rString 要填充的初始字符串
     * @param rLength 填充后的长度
     * @param rPad    填充字符
     * @return String
     * @author: tanyw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-21 tanyw 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String padLeading(final String rString, final int rLength, final String rPad) {
        String lTmpPad = "";

        final String lTmpStr = StrUtil.strnull(rString);

        if (lTmpStr.length() >= rLength) {
            return lTmpStr.substring(0, lTmpStr.length());
        } else {
            for (int gCnt = 1; gCnt <= rLength - lTmpStr.length(); gCnt++) {
                lTmpPad = lTmpPad + rPad;
            }
        }
        return lTmpPad + lTmpStr;
    }

    /**
     * 判断数组是否存在某个字符串，并返回索引.
     *
     * @param source String[]
     * @param subStr String
     * @return int
     * @author: panchh
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-1 panchh 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static int contains(final String[] source, final String subStr) {
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals(subStr)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法功能:
     * 判断一个对象或者是字符串是否为空
     *
     * @param
     * @param str
     * @return
     * @author: Liuzhuangfei
     * @修改记录： ==============================================================<br>
     * 日期:2010-12-17 Liuzhuangfei 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static boolean isNullOrEmpty(final Object str) {
        boolean result = false;
        if (str == null || "null".equals(str) || "".equals(str.toString().trim())) {
            result = true;
        }
        return result;
    }

    /**
     * 方法功能:
     * 将时间的格式转换成YYYYMMDDHHMMSS .
     *
     * @param aDate aDate
     * @return String String
     * @author: Liuzhuangfei
     * @修改记录： ==============================================================<br>
     * 日期:2011-2-26 Liuzhuangfei 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static final String dateTimeToStr(String aDate) {
        String returnValue = "";
        if (aDate != null && aDate.length() < 14) {
            StrUtil.padTrailing(aDate, 14, "0");
        }
        String str = aDate.replaceAll("-", "");
        String str1 = str.replaceAll(" ", "");
        String str2 = str1.replaceAll(":", "");
        returnValue = str2.substring(0, 14);

        return (returnValue);
    }

    /**
     * getLength.
     *
     * @param str String
     * @return
     * @author wuyx
     * 2011-3-23 wuyx
     */
    public static final int getLength(String str) {
        return str == null ? 0
                : str.length();
    }

    /**
     * 判断是否包括中文字符串.
     *
     * @param str 字符串
     * @return 是否中文字符串
     * @author zfz
     * 2011-4-25 zfz
     */
    public static boolean isContainChnStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fbb]+")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 正则表达式校验公用方法，传入值 和 正则表达式，返回校验结构
     * .
     *
     * @param value
     * @param expr
     * @return
     * @author wangzy
     * 2011-5-25 wangzy
     */
    static public boolean matchExpr(String value, String expr) {
        // 实现正则表达式，属性校验功能
        boolean result = true;
        Pattern pattern;
        try {

            pattern = patterCompiler.compile(expr);
            result = patterMatcher.matches(value.toString(), pattern);

        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }

    /**
     * 将HTML编码转换成普通字符串
     * .
     *
     * @param src String
     * @return
     * @author wangjianjun
     * 2011-6-7 wangjianjun
     */
    public static String strConverFromHtml(String src) {
        StringBuffer sb = new StringBuffer();
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("&[a-zA-Z]*;");
        Matcher m = p.matcher(src);
        int pos1 = 0;
        while (m.find(pos1)) {
            int pos2 = m.start();
            sb.append(src.substring(pos1, pos2));
            String entity = m.group().toLowerCase();
            if ("&#39;".equals(entity)) {
                sb.append("\'");
            } else if ("&#34;".equals(entity)) {
                sb.append("\"");
            } else if ("&lt;".equals(entity)) {
                sb.append("<");
            } else if ("&gt;".equals(entity)) {
                sb.append(">");
            } else if ("&nbsp;".equals(entity)) {
                sb.append(" ");
            } else if ("&amp;".equals(entity)) {
                sb.append("&");
            } else {
                sb.append("[UNKNOWN] ");
            }
            pos1 = m.end();
        }
        sb.append(src.substring(pos1));
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "&lt;temp&gt;测试&amp;一下是&amp;否有效&lt;/temp&gt;";
        System.out.println(strConverFromHtml(str));
    }

    /**
     * 方法功能:从XML字符串中，读取指定标识的内容.
     *
     * @param inXML        输入xml串
     * @param maskStartStr 起始掩码
     * @param maskEndStr   结束掩码
     * @return String 返回内容
     * @throws ManagerException ManagerException
     * @author: Liuzhuangfei
     * @修改记录： ==============================================================<br>
     * 日期:2011-10-8 Liuzhuangfei 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String getXmlContent(String inXML, String maskStartStr, String maskEndStr)
            throws ManagerException {
        String outStr = "";
        int startIndex = -1;
        int endIndex = -1;

        if (inXML != null) {
            startIndex = inXML.indexOf(maskStartStr);
            endIndex = inXML.indexOf(maskEndStr);

            if (startIndex != -1) {
                int contentStart = inXML.indexOf('>', startIndex) + 1;
                outStr = inXML.substring(contentStart, endIndex);
            }
        }

        return outStr;
    }

    /**
     * 方法功能: 获取下级的列表格式.
     *
     * @param inXml        输入xml串
     * @param maskStartStr 起始掩码
     * @param maskEndStr   结束掩码
     * @return List 列表
     * @author: Liuzhuangfei
     * @修改记录： ==============================================================<br>
     * 日期:2011-10-8 Liuzhuangfei 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static List<String> getSubXmlList(String inXml, String maskStartStr, String maskEndStr) {

        String tmp = inXml.replace(maskEndStr, maskStartStr);
        tmp += " ";
        String[] list = tmp.split(maskStartStr);
        List ret = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {
            if (i != 0 && i != list.length - 1 && !list[i].trim().equals("")) {
                ret.add(list[i]);
            }
        }
        return ret;
    }

    /**
     * 方法功能:
     * .
     *
     * @param content XML的所有内容
     * @param element 需要读取的元素
     * @return String 返回元素内容
     * @throws ManagerException ManagerException
     * @author: zhengyan
     * @修改记录： ==============================================================<br>
     * 日期:2011-10-12 zhengyan 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String readXmlContent(String content, String element) throws ManagerException {
        if (isNullOrEmpty(element) || isNullOrEmpty(content)) {
            return null;
        }
        StringBuffer startMark = new StringBuffer(element);
        startMark.insert(0, "<");
        startMark.append(">");
        StringBuffer endMark = new StringBuffer(startMark.toString());
        endMark.insert(1, "/");
        return StrUtil.getXmlContent(content, startMark.toString(), endMark.toString());
    }

    /**
     * 方法功能:
     * 去除字符串头尾的空格.
     *
     * @param source
     * @return
     * @author: linzhiqiang
     * @修改记录： ==============================================================<br>
     * 日期:2012-2-23 linzhiqiang 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strTrim(String source) {
        if (source == null) {
            return null;
        }

        String str = source.trim();
        return str;
    }

    /**
     * @param args
     */
    public static String numtochinese(String input) {
        String s1 = "零壹贰叁肆伍陆柒捌玖";
        String s4 = "分角整元拾佰仟万拾佰仟亿拾佰仟";
        String temp = "";
        String result = "";
        if (input == null)
            return null;
        //return "输入字串不是数字串只能包括以下字符（'0'～'9'，'.')，输入字串最大只能精确到仟亿，小数点只能两位！";
        temp = input.trim();
        float f;
        try {
            f = Float.parseFloat(temp);

        } catch (Exception e) {
            return result;
            //return "输入字串不是数字串只能包括以下字符（'0'～'9'，'.')，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        int len = 0;
        if (temp.indexOf(".") == -1)
            len = temp.length();
        else
            len = temp.indexOf(".");
        if (len > s4.length() - 3)
            return result;//("输入字串最大只能精确到仟亿，小数点只能两位！");
        int n1, n2 = 0;
        String num = "";
        String unit = "";

        for (int i = 0; i < temp.length(); i++) {
            if (i > len + 2) {
                break;
            }
            if (i == len) {
                continue;
            }
            n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
            num = s1.substring(n1, n1 + 1);
            n1 = len - i + 2;
            unit = s4.substring(n1, n1 + 1);
            result = result.concat(num).concat(unit);
        }
        if ((len == temp.length()) || (len == temp.length() - 1))
            result = result.concat("整");
        if (len == temp.length() - 2)
            result = result.concat("零分");
        return result;
    }

    /**
     * 方法功能: .
     * 去除字符之间的空格
     *
     * @return
     * @author chenmq
     * 2012-6-21 chenmq
     * @修改记录： ==============================================================<br>
     * 日期:2012-6-21 chenmq 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String removeSpace(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        return str.replaceAll(" ", "");
    }

    public static String blob2string(Blob blob) {
        InputStream ins = null;
        String contentString = "";
        try {
            ins = blob.getBinaryStream();
            byte[] c = new byte[(int) blob.length()];
            ins.read(c);
            contentString = new String(c);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return contentString;
        }
    }

    /**
     * 获取宇宙唯一码.
     *
     * @return
     * @版权：福富软件 版权所有 (c) 2012
     * @author Liuzhuangfei
     * @version Revision 1.0.0
     * @see:
     * @创建日期：2013-1-21
     * @功能说明：
     */
    public static String getUUID() {
        UUID uuid = java.util.UUID.randomUUID();
        String uuidStr = uuid.toString().toUpperCase();
        if (isNullOrEmpty(uuidStr)) {
            return "";
        }
        return uuidStr;
    }

    /**
     * @param strs 待转化字符串
     * @return
     * @author estone
     * @description 下划线格式字符串转换成驼峰格式字符串
     * eg: player_id -> PlayerId;<br>
     * player_name -> PlayerName;
     */
    public static String underScore2CamelCase(String strs) {
        String[] elems = strs.split("_");
        for (int i = 0; i < elems.length; i++) {
            elems[i] = elems[i].toLowerCase();
            String elem = elems[i];
            char first = elem.toCharArray()[0];
            elems[i] = "" + (char) (first - 32) + elem.substring(1);
        }
        StringBuffer sb = new StringBuffer();
        for (String e : elems) {
            sb.append(e);
            // System.out.print(e);
        }
        return sb.toString();
    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        // hexString = hexString.toUpperCase(); //如果是大写形式
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    /**
     * 判断是否为纯数字字符串
     *
     * @param str
     * @return
     * @author chenye
     * 2014-7-29 chenye
     */
    public static boolean isNumeric(String str) {
        if (!StrUtil.isNullOrEmpty(str)) {
            return str.matches("[0-9]+");
        }
        return false;
    }
}
