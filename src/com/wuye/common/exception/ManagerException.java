package com.wuye.common.exception;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.exception.ManagerException.java
 * @所含类：ManagerException
 * @author: hzw
 * @version: V0.90
 * @see:
 * @创建日期：2007-9-14
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-14 hzw 创建 操作类异常处理
 * =============================================================<br>
 */

public class ManagerException extends BaseException {

    /**
     * @param anErrorCode  String
     * @param aManagerName String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 hzw 创建方法，并实现其功能 构造器，接受错误码、操作类名称作为参数
     * ==============================================================<br>
     */

    public ManagerException(String anErrorCode, String aManagerName) {
        super.setMessageKey(anErrorCode);
        this.setManagerName(aManagerName);
    }

    /**
     * @param anErrorCode  String
     * @param anException  Exception
     * @param aManagerName String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 hzw 创建方法，并实现其功能 构造器，接受错误码，原始异常，操作类名称作为参数
     * ==============================================================<br>
     */
    public ManagerException(String anErrorCode, Exception anException,
                            String aManagerName) {
        super.setMessageKey(anErrorCode);
        super.setRootCause(anException);
        this.setManagerName(aManagerName);
    }

    /**
     * @param aManagerName String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 hzw 创建方法，并实现其功能 设置对应操作类名
     * ==============================================================<br>
     */
    public void setManagerName(String aManagerName) {
        super.setPlusInfo("(" + aManagerName + ")");
    }

    /**
     * @return String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 hzw 创建方法，并实现其功能 取出对应操作类名
     * ==============================================================<br>
     */
    public String getManagerName() {
        return super.getPlusInfo();
    }

}
