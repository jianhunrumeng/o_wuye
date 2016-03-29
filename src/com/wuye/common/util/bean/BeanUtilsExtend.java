package com.wuye.common.util.bean;

import com.wuye.common.exception.RtManagerException;
import com.wuye.common.util.date.DateUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.wuye.common.annotation.Invisible;
//import com.wuye.common.annotation.PropertyCopy;

/**
 * @版权：福富软件 版权所有 (c) 2008
 * @文件：com.ffcs.crm.common.util.obj.BeanUtilEx.java
 * @所含类：BeanUtilEx
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
public class BeanUtilsExtend extends BeanUtils {

    private static Map cache = new HashMap();
    private static Log logger = LogFactory.getFactory().getInstance(
            BeanUtilsExtend.class);

    private BeanUtilsExtend() {
    }

    static {
        // 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        // 这个class，是目标对象的类型,
        ConvertUtils.register(new SqlDateConverter(), java.sql.Date.class);
        // ConvertUtils.register(new SqlTimestampConverter(),
        // java.sql.Timestamp.class);,to-do
        // 注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空
        ConvertUtils.register(new UtilDateConverter(), java.util.Date.class);
        ConvertUtils.register(new StringConvert(), java.lang.String.class);

        // add by g.caijx
        ConvertUtils.register(new LongConverter(null), java.lang.Long.class);
        ConvertUtils.register(new IntegerConverter(null),
                java.lang.Integer.class);
    }

    public static void copyProperties(Object target, Object source)
            throws InvocationTargetException, IllegalAccessException {
        org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
    }

    public static void copyProperty(Object bean, String name, Object value)
            throws InvocationTargetException, IllegalAccessException {
        org.apache.commons.beanutils.BeanUtils.copyProperty(bean, name, value);
    }

    /**
     * copyProperties, 不复制值为 NULL 的属性
     *
     * @param target
     * @param source
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author: g.caijx
     * @修改记录： ==============================================================<br>
     * 日期:Jun 6, 2011 g.caijx 创建方法，并实现其功能
     * <p/>
     * ==============================================================<br>
     */
    public static void copyPropertiesIgnoreNull(Object target, Object source)
            throws InvocationTargetException, IllegalAccessException {
        if (source != null) {
            PropertyUtilsBean pub = BeanUtilsBean.getInstance()
                    .getPropertyUtils();
            PropertyDescriptor[] origDescriptors = pub
                    .getPropertyDescriptors(source);

            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (pub.isReadable(source, name)
                        && pub.isWriteable(target, name)) {
                    try {
                        Object value = pub.getSimpleProperty(source, name);
                        if (value != null) {
                            copyProperty(target, name, value);
                        }
                    } catch (NoSuchMethodException e) {
                        // Should not happen
                    }
                }
            }
        }
    }

    public static void copyProperties(Object target, Object source, String annoName)
            throws InvocationTargetException, IllegalAccessException {/*
        if (source != null && !StrUtil.isNullOrEmpty(annoName)) {
			PropertyUtilsBean pub = BeanUtilsBean.getInstance()
					.getPropertyUtils();
			PropertyDescriptor[] origDescriptors = pub
					.getPropertyDescriptors(source);

			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (pub.isReadable(source, name)
						&& pub.isWriteable(target, name)) {
					try {
						Field field = null;
						try{
							field = target.getClass().getDeclaredField(name);
						}catch(NoSuchFieldException e){
							continue;
						}
						if (field.isAnnotationPresent(Invisible.class)){
							Invisible anno = field.getAnnotation(Invisible.class);
							String[] annoValue = anno.value();
				            for (int i1 = 0; i1 < annoValue.length; i1++) {
				            	if (annoName.equals(annoValue[i1])){
				            		Object value = pub.getSimpleProperty(source, name);
				            		copyProperty(target, name, value);
				            	}
				            }
						}
						
					} catch (Exception e) {
						throw new RtManagerException("拷贝会是时出错", e);
					}
				}
			}
		}
	*/
    }

    public static void copyProperties(Object target, Object source,
                                      String[] properties) {
        if (source != null) {
            if (properties != null && properties.length != 0) {
                for (String property : properties) {
                    Object value = BeanUtilsExtend.getPropertyValue(source, property);
                    BeanUtilsExtend.populateProperty(target, source, property, value);
                }
            } else {
                PropertyUtilsBean pub = BeanUtilsBean.getInstance()
                        .getPropertyUtils();
                PropertyDescriptor[] origDescriptors = pub
                        .getPropertyDescriptors(source);

                for (int i = 0; i < origDescriptors.length; i++) {
                    String name = origDescriptors[i].getName();
                    if ("class".equals(name)) {
                        continue; // No point in trying to set an object's class
                    }
                    if (pub.isReadable(source, name)
                            && pub.isWriteable(target, name)) {
                        try {
                            Object value = pub.getSimpleProperty(source, name);
                            copyProperty(target, name, value);
                        } catch (Exception e) {
                            throw new RtManagerException("拷贝会是时出错", e);
                        }
                    }
                }
            }
        }
    }

    public static void populateProperty(Object dest, Object orig,
                                        String propertyName, Object value) {
        try {
            Class<?> type = BeanUtilsExtend.getPropertyType(dest, propertyName);
            // 空字符串时置空 - renl 20120912
            if (value == null || String.valueOf(value).trim().length() == 0) {
                value = null;
            } else if (type.isAssignableFrom(Date.class)) {
                try {
                    value = DateUtil.convertStringToDate("yyyyMMddHHmmss",
                            String.valueOf(value));
                } catch (ParseException e) {
                    throw new RtManagerException("无效的日期格式，应为：yyyyMMddHHmmss", e);
                }
            } else if (type.isAssignableFrom(Integer.class)) {
                value = Integer.valueOf(String.valueOf(value));
            } else if (type.isAssignableFrom(Long.class)) {
                value = Long.valueOf(String.valueOf(value));
            } else if (type.isAssignableFrom(Double.class)) {
                value = Double.valueOf(String.valueOf(value));
            } else if (type.isAssignableFrom(Timestamp.class)) {
                Date date = DateUtil.convertStringToDate("yyyy-MM-dd",
                        String.valueOf(value));
                //yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错

                value = new Timestamp(date.getTime());
                //Timestamp.valueOf(String.valueOf(value));
            } else if (type.isAssignableFrom(Float.class)) {
                value = Float.valueOf(String.valueOf(value));
            }

            PropertyUtils.setProperty(dest, propertyName, value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RtManagerException("设置值时出错", e);
        }
    }

    public static Object getPropertyValue(Object bean, String name) {
        try {
            Object object = PropertyUtils.getProperty(bean, name);
            Class<?> type = BeanUtilsExtend.getPropertyType(bean, name);
            if (object != null) {
                if (type.isAssignableFrom(Date.class)) {
                    return DateUtil.dateTimeToStr((Date) object);
                }
            }
            return object;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RtManagerException("取值时出错", e);
        }
    }

    public static Class<?> getPropertyType(Object bean, String name) {
        try {
            return PropertyUtils.getPropertyDescriptor(bean, name).getPropertyType();
        } catch (Exception e) {
            throw new RtManagerException("取属性类型时出错", e);
        }
    }

    public static void populate(Object destObj, JSONObject srcObj, String[] property) {
        if (destObj == null || srcObj == null || property == null || property.length == 0) {
            return;
        }
        for (String prop : property) {
            Object value = srcObj.get(prop);
            populateProperty(destObj, srcObj, prop, value);
        }
    }
}
