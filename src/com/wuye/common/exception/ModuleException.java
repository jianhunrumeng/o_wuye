package com.wuye.common.exception;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.exception.ModuleException.java
 * @所含类：ModuleException
 * @author: hzw
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-14
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-14 hzw 创建
 * 异常处理
 * =============================================================<br>
 */
public class ModuleException extends BaseException {

    /**
     * @param anErrorCode String
     * @param aModuleName String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14      hzw         创建方法，并实现其功能
     * 构造器，接受错误码、模块名称作为参数
     * ==============================================================<br>
     */

    public ModuleException(String anErrorCode, String aModuleName) {
        super.setMessageKey(anErrorCode);
        this.setModule(aModuleName);
    }

    /**
     * @param anErrorCode String
     * @param anException Exception
     * @param aModuleName String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14      hzw         创建方法，并实现其功能
     * 构造器，接受错误码，原始异常，模块名称作为参数
     * ==============================================================<br>
     */
    public ModuleException(String anErrorCode, Exception anException,
                           String aModuleName) {
        super.setMessageKey(anErrorCode);
        super.setRootCause(anException);
        this.setModule(aModuleName);
    }

    /**
     * @param aModuleName String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14      hzw         创建方法，并实现其功能
     * 设置对应模块名
     * ==============================================================<br>
     */

    public void setModule(String aModuleName) {
        super.setPlusInfo("(" + aModuleName + ")");
    }

    /**
     * @return String
     * @author: hzw
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14      hzw         创建方法，并实现其功能
     * 取出对应模块名
     * ==============================================================<br>
     */
    public String getModule() {
        return super.getPlusInfo();
    }

}
