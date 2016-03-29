package com.wuye.common.util.bean;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @版权：福富软件 版权所有 (c) 2008
 * @文件：com.ffcs.crm.common.util.obj.UtilDateConverter.java
 * @所含类：UtilDateConverter
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
public class UtilDateConverter implements Converter {
    public UtilDateConverter() {

        this.defaultValue = null;
        this.useDefault = false;

    }

    public UtilDateConverter(Object defaultValue) {

        this.defaultValue = defaultValue;
        this.useDefault = true;

    }

    private Object defaultValue = null;

    private boolean useDefault = true;

    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return (value);
        }

        String strValue = value.toString();
        if (strValue == null)
            return null;
        if (strValue.equals(""))
            return null;
        Date theDate;

        if (strValue.length() > 10) {
            try {
                String str = strValue.substring(4, 5);
                SimpleDateFormat theFormat = new SimpleDateFormat("yyyy" + str + "MM" + str + "dd" + " " + "HH" + ":" + "mm" + ":" + "ss");
                theDate = theFormat.parse(strValue);
            } catch (Exception ex) {
                if (useDefault) {
                    return (defaultValue);
                } else {
                    throw new ConversionException(ex);
                }
            }
        } else {
            try {
                String str = strValue.substring(4, 5);
                SimpleDateFormat theFormat = new SimpleDateFormat("yyyy" + str + "MM" + str + "dd");
                theDate = theFormat.parse(strValue);
            } catch (Exception ex) {
                if (useDefault) {
                    return (defaultValue);
                } else {
                    throw new ConversionException(ex);
                }
            }
        }

        return theDate;

    }
}
