package com.wuye.common.util.encrypt;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.util.encrypt.IEncrypt.java
 * @所含类：IEncrypt
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-20
 * @功能说明：加密和解密的接口定义
 * @修改记录： =============================================================<br>
 * 日期:2007-9-20 wuq 创建
 * <p/>
 * =============================================================<br>
 */
public interface IEncrypt {

    /**
     * 生成加密字符串
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     */
    public String encrypt(String str);

    /**
     * 生成解密字符串
     *
     * @param enStr 待解密的字符串
     * @return 解密后的字符串
     * @throws DecryptException 如果加密是单向加密，而用本方法解密，<br>
     *                          则抛出解密异常DecryptException
     */
    public String decrypt(String enStr) throws DecryptException;
}