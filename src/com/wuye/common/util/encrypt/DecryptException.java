package com.wuye.common.util.encrypt;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.util.encrypt.DecryptException.java
 * @所含类：DecryptException
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-20
 * @功能说明： 解密异常类。对于那些不可逆转的加密算法,比如单向散列算法等,应用 解密时抛出异常.
 * @修改记录： =============================================================<br>
 * 日期:2004-12-10 jerry 创建
 * <p/>
 * =============================================================<br>
 */
public class DecryptException extends java.lang.Exception {
    /**
     *
     */
    private static final long serialVersionUID = 9112090718854660971L;

    /**
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2004-12-10 jerry 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public DecryptException() {
        super();
    }

    /**
     * @param msg String
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2004-12-10 jerry 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public DecryptException(String msg) {
        super(msg);
    }
}
