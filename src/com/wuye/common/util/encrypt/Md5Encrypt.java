package com.wuye.common.util.encrypt;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.ffcs.crm.common.util.encrypt.Md5Encrypt.java
 * @所含类：Md5Encrypt
 * @author: jerry
 * @version: V1.0
 * @see:
 * @创建日期：2004-12-10
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2004-12-10 jerry 创建
 * =============================================================<br>
 */
public class Md5Encrypt implements IEncrypt {

    /**
     * static Md5Encrypt.
     */
    private static Md5Encrypt md5Encrypt = new Md5Encrypt();

    /**
     * Md5.
     *
     * @param str String
     * @return String
     * @author wuyx
     * 2011-5-23 wuyx
     */
    public static String md5(String str) {
        return md5Encrypt.encrypt(str);
    }

    /**
     * @param originStr String
     * @return String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 wuq 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public String encrypt(final String originStr) {
        final MD5 tool = new MD5();
        return tool.getMD5ofStr(originStr);
    }

    /**
     * @param str String
     * @return String
     * @throws DecryptException DecryptException
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public String decrypt(final String str) throws DecryptException {
        throw new DecryptException(
                "md5 are secure one-way hash arithemetic,could not be decrypted ");
    }
}
