package com.wuye.common.exception;

import java.sql.SQLException;

/**
 * @版权：物业软件 版权所有 (c) 2007
 * @文件：com.wuye.common.exception.DBException.java
 * @所含类：DBException
 * @author: wuq
 * @version: V1.0
 * @see:
 * @创建日期：2007-9-14
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2007-9-14 wuq 创建
 * <p/>
 * =============================================================<br>
 */
public class DBException extends BaseException {
    /**
     * @param aSqlException SQLException
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能 构造器，接受一个SQLException作为参数
     * ==============================================================<br>
     */
    public DBException(SQLException aSqlException) {
        super(aSqlException);
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
        int sqlErrorCode = ((SQLException) super.getRootCause()).getErrorCode();
        String errorBundle = "sql.error_";
        String code = "";
        switch (sqlErrorCode) {
            case 2008 - 239 - 201 - 217 - 244:
                code = errorBundle + sqlErrorCode;
                break;
            default:
                code = errorBundle + "default";
                break;
        }
        return code;
    }

    /**
     * @return String
     * @author: wuq
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-14 wuq 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public String getState() {
        String state = ((SQLException) super.getRootCause()).getSQLState();
        return state;
    }

}
