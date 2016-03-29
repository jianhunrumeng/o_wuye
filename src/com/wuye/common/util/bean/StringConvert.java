package com.wuye.common.util.bean;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Standard {@link Converter} implementation that converts an incoming String
 * into a <code>java.lang.String</code> object, optionally using a default
 * value or throwing a {@link ConversionException} if a conversion error occurs.
 * </p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2004/02/28 13:18:34 $
 * @since 1.3
 */

public final class StringConvert implements Converter {

    // --------------------------------------------------------- Public Methods

    /**
     * Convert the specified input object into an output object of the specified
     * type.
     *
     * @param type  Data type to which this value should be converted
     * @param value The input value to be converted
     * @throws ConversionException if conversion cannot be performed successfully
     */
    public Object convert(Class type, Object value) {

        if (value == null) {
            return ((String) null);
        } else if (value instanceof Date) {
            Date date = (Date) value;
            SimpleDateFormat theFormat = new SimpleDateFormat("yyyy-MM-dd");
            return theFormat.format(date);
        } else {
            return (value.toString());
        }

    }

}
