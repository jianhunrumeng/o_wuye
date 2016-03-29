/**
 * =======================================================================<br>
 * 版权： 物业软件 版权所有 (c) 2007 - 2008<br>
 * 文件包： com.wuye.common.exception
 * 所含类： ExceptionHandler
 * 编写人员：陈军
 * 创建日期：2007-03-27
 * 功能说明: 使用Throw通知类型来实现Advice
 * 更新记录：
 * 日期          作者           内容<br>
 * =======================================================================<br>
 * 2007-03-29   陈军         创建新文件，并实现afterThrowing方法
 * <p/>
 * =======================================================================<br>
 */
package com.wuye.common.exception;

import com.wuye.common.util.date.DateUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

import java.io.Writer;
import java.lang.reflect.Method;

/**
 *
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.exception.ExceptionHandler.java
 * @所含类：ExceptionHandler
 * @author: tanyw
 * @version: V1.0
 * @see:
 * @创建日期：2007-4-2
 * @功能说明：
 *
 * @修改记录：
 *
 * =============================================================<br>
 * 日期:2007-4-2 tanyw 创建 异常捕捉并输出信息
 * =============================================================<br>
 */
public class ExceptionHandler implements ThrowsAdvice {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     *
     * @param method Method
     * @param args Object[]
     * @param target Object
     * @param subclass Throwable
     * @throws Throwable Throwable
     * @author: tanyw
     * @修改记录：
     *
     * ==============================================================<br>
     * 日期:2007-4-2 tanyw 创建方法，并实现其功能
     *
     * ==============================================================<br>
     */
    public void afterThrowing(Method method, Object[] args, Object target,
                              Throwable subclass) throws Throwable {

        try {

            logger
                    .log(Level.ERROR,
                            "---------------------当前方法抛出异常------------------------------------------------");
            logger.log(Level.ERROR, "执行时间: " + DateUtil.getNowCustomDate()
                    + " " + DateUtil.getNowCustomTime());

            logger.log(Level.ERROR, "类名: " + method.getDeclaringClass()
                    + "  方法名:" + method.getName());
            logger.log(Level.ERROR, " 执行时有异常抛出....");
            logger.log(Level.ERROR, " 异常信息是:" + subclass);

            StackTraceElement stackTraceElement = subclass.getStackTrace()[0];// 得到异常棧的首个元素

            logger.log(Level.ERROR, "文件名:" + stackTraceElement.getFileName()); // 打印文件名
            logger
                    .log(Level.ERROR, "出错方法:"
                            + stackTraceElement.getMethodName());// 打印出错方法
            logger
                    .log(Level.ERROR, "出错行号:"
                            + stackTraceElement.getLineNumber());// 打印出错行号

            logger
                    .log(
                            Level.ERROR,
                            "-----------------------------------------------------------------------------------");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.ERROR, "输出异常日志时出错!");
        }
        try {
            logger.log(Level.DEBUG, " 输入信息是:");
            if (args != null) {
                Writer inputParam = new java.io.StringWriter();
                org.exolab.castor.xml.Marshaller.marshal(args, inputParam);
                logger.log(Level.DEBUG, inputParam);
            }
            // logger.log(Level.DEBUG, " 输出信息是:");
            // if (target != null) {
            // Writer outputParam = new java.io.StringWriter();
            // org.exolab.castor.xml.Marshaller.marshal(target, outputParam);
            // logger.log(Level.DEBUG, outputParam);
            // }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.DEBUG, "输入转换成XML时发生异常!");
        }

    }

}