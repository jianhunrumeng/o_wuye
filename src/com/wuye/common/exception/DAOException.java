package com.wuye.common.exception;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.exception.DAOException.java
 * @所含类：DAOException
 * @author: JIangZhiGang
 * @version: V1.0
 * @创建日期：2007-9-14
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-14 wuq 创建
 * <p/>
 * =============================================================<br>
 */

public class DAOException extends BaseException {

    /**
     * @param anErrorMsg String 错误信息
     * @param aRootCause hrowable 异常
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能 构造函数
     * ==============================================================<br>
     */

    public DAOException(String anErrorMsg, Throwable aRootCause) {
        this.setErrorMsg(anErrorMsg);
        super.setRootCause(aRootCause);
    }

    /**
     * @param anErrorMsg  String 错误信息
     * @param anErrorCode String 错误码
     * @param aRootCause  Throwable 异常
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能 构造函数
     * ==============================================================<br>
     */

    public DAOException(String anErrorMsg, String anErrorCode,
                        Throwable aRootCause) {
        this.setErrorMsg(anErrorMsg);
        super.setMessageKey(anErrorCode);
        super.setRootCause(aRootCause);
    }

    /**
     * @param anErrorMsg String 错误信息
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能 设置错误信息
     * ==============================================================<br>
     */

    public void setErrorMsg(String anErrorMsg) {
        super.setPlusInfo(anErrorMsg);
    }

    /**
     * @return String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能 取得错误信息
     * ==============================================================<br>
     */

    public String getErrorMsg() {
        return super.getPlusInfo();
    }
}
