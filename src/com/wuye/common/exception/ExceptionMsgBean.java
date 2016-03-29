package com.wuye.common.exception;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.exception.ExceptionMsgBean.java
 * @所含类：ExceptionMsgBean
 * @作者: hzw
 * @创建日期：2007-9-14
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-14 hzw 创建 物业软件异常处理组件
 * =============================================================<br>
 */

public class ExceptionMsgBean {

    public static boolean flag = true;

    private String exceptionType;

    private int closeType;

    private String target;

    private String uri;

    private int reopen;

    private int openType;

    private String plusInfo;

    /**
     * @param flag boolean
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @return boolean
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public synchronized boolean getFlag() {
        return this.flag;
    }

    /**
     * @return String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getExceptionType() {
        return exceptionType;
    }

    /**
     * @param exceptionType String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * @return int
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getCloseType() {
        return closeType;
    }

    /**
     * @param closeType int
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setCloseType(int closeType) {
        this.closeType = closeType;
    }

    /**
     * @return String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param aTarget String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setTarget(String aTarget) {
        this.target = aTarget;
    }

    /**
     * @return String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return int
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getReopen() {
        return this.reopen;
    }

    /**
     * @param reopen int
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setReopen(int reopen) {
        this.reopen = reopen;
    }

    /**
     * @return int
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public int getOpenType() {
        return this.openType;
    }

    /**
     * @param openType int
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setOpenType(int openType) {
        this.openType = openType;
    }

    /**
     * @return String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getPlusInfo() {
        if (reopen == 0) {
            flag = true;
        }
        reopen = 0;
        return this.plusInfo;
    }

    /**
     * @param plusInfo String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-17 hzw 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public void setPlusInfo(String plusInfo) {
        this.plusInfo = plusInfo;
    }
}
