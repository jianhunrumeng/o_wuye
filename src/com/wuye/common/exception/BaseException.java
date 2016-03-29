package com.wuye.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @文件：com.wuye.common.exception.BaseException.java
 * @所含类：BaseException
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-13
 * @功能说明： 异常处理组件
 * @修改记录： =============================================================<br>
 * 日期:2007-9-13 wuq 创建
 * <p/>
 * =============================================================<br>
 */

public class BaseException extends Exception {
    /**
     * serialVersionUID long
     */
    private static final long serialVersionUID = 5232985317228036604L;

    /**
     * int MANUAL_CLOSE
     */
    public static final int MANUAL_CLOSE = -1;

    /**
     * int TEN_SENCONDS_CLOSE
     */
    public static final int TEN_SENCONDS_CLOSE = 10;

    /**
     * int NEW_OPEN
     */
    public static final int NEW_OPEN = 1;

    /**
     * int PRESENT_OPEN
     */
    public static final int PRESENT_OPEN = 0;

    /**
     * int PARENT_OPEN
     */
    public static final int PARENT_OPEN = -1;

    /**
     * int ERROR_TYPE
     */
    public static final int ERROR_TYPE = -1;

    /**
     * int INFO_TYPE
     */
    public static final int INFO_TYPE = 1;

    /**
     * int WARN_TYPE
     */
    public static final int WARN_TYPE = 0;

    /**
     * Throwable rootCause
     */
    protected Throwable rootCause = null;

    private Collection exceptions = new ArrayList();
    private Object errObj = null;

    private String messageKey = null;

    private int exceptionType = INFO_TYPE;

    private int openType = PRESENT_OPEN; // web打开异常页面的方式

    private int closeType = 5; // 当前窗口的关闭时显示的时间

    private Object[] messageArgs = null;

    private String plusInfo = null;

    /**
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public BaseException() {
        super();
    }

    /**
     * @param aCause 参数
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public BaseException(Throwable aCause) {
        this.rootCause = aCause;
    }

    /**
     * @return Collection
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public Collection getCollection() {
        return exceptions;
    }

    /**
     * @param ex 参数
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void addException(BaseException ex) {
        exceptions.add(ex);
    }

    /**
     * @param anExceptionType 参数
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setExceptionType(int anExceptionType) {
        exceptionType = anExceptionType;
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getExceptionType() {
        return exceptionType;
    }

    /**
     * @param anOpenType 异常页面的方式
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * 设置打开异常页面的方式，NEW_OPEN,PRESENT_OPEN,PARENT_OPEN分别代表新窗口，当前窗口，父窗口打开
     * ==============================================================<br>
     */

    public void setOpenType(int anOpenType) {
        openType = anOpenType;
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getOpenType() {
        return openType;
    }

    /**
     * @param aCloseType int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * 设置当前窗口的关闭方式，参数为正整数n时表示n秒后自动关闭，参数为MANUAL_CLOSE表示手动关闭
     * ==============================================================<br>
     */

    public void setCloseType(int aCloseType) {
        closeType = aCloseType;
    }

    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getCloseType() {
        return closeType;
    }

    /**
     * @param key String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setMessageKey(String key) {
        this.messageKey = key;
    }

    /**
     * @return String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * @param args Object[]
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setMessageArgs(Object[] args) {
        this.messageArgs = args;
    }

    /**
     * @return Object[]
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public Object[] getMessageArgs() {
        return messageArgs;
    }

    /**
     * @param anException Throwable
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setRootCause(Throwable anException) {
        rootCause = anException;
    }

    /**
     * @return Throwable
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public Throwable getRootCause() {
        return rootCause;
    }

    /**
     * @param plusInfo String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setPlusInfo(String plusInfo) {
        this.plusInfo = plusInfo;
    }

    /**
     * @return String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getPlusInfo() {
        return this.plusInfo;
    }

    /**
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * @param outStream PrintStream
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void printStackTrace(PrintStream outStream) {
        printStackTrace(new PrintWriter(outStream));
    }

    /**
     * @param writer PrintWriter
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void printStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
        if (getRootCause() != null) {
            getRootCause().printStackTrace(writer);
        }
        writer.flush();
    }

    /**
     * 传递错误对象信息，如Map对象等
     *
     * @return Object 错误对象信息
     * @author: 刘育强
     */
    public Object getErrObj() {
        return errObj;
    }

    /**
     * 传递错误对象信息，如Map对象等
     *
     * @param Object 错误对象信息
     * @author: 刘育强
     */
    public void setErrObj(Object errObj) {
        this.errObj = errObj;
    }
}
