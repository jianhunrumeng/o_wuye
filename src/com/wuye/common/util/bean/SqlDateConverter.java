package com.wuye.common.util.bean;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @版权：福富软件 版权所有 (c) 2008
 * @文件：com.ffcs.crm.common.util.obj.SqlDateConverter.java
 * @所含类：SqlDateConverter
 * @author: peilh
 * @version: V1.0
 * @see:
 * @创建日期：2008-8-23
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:2008-8-23 peilh 创建
 * <p/>
 * =============================================================<br>
 */
public class SqlDateConverter implements Converter {
    private final static SimpleDateFormat DATE_FORMATE_SHOW = new SimpleDateFormat(
            "yyyy-MM-dd");

    public Object convert(Class type, Object value) {
        if (type.equals(java.sql.Date.class)) {
            if (value != null) {
                try {
                    return DATE_FORMATE_SHOW.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
