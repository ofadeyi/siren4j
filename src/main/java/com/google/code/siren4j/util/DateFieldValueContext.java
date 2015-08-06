package com.google.code.siren4j.util;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class DateFieldValueContext extends AbstractFieldValueContext {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ssZ";
    public static final String DATETIME_FORMAT = DATE_FORMAT +"'T'"+ TIME_FORMAT;

    DateFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static DateFieldValueContext newInstance() {
        return new DateFieldValueContext(EnumFieldValueContext.newInstance(), Date[].class);
    }

    @Override
    boolean canProcess(Class<?> fieldType) {
        if (Date.class.equals(fieldType)) {
            return true;
        }
        return false;
    }

    @Override
    Date parseType(Object value) {
        if (value == null) {
            return null;
        }
        if (Date.class.isAssignableFrom(value.getClass())) {
            return (Date) value;
        }
        if (Number.class.isAssignableFrom(value.getClass())) {
//            Instant instant = Instant.ofEpochMilli(((Number)value).longValue());
//            Date result = Date.from(instant);
            Date result = DateTime.now().withMillis(((Number)value).longValue()).toDate();
            return result;
        }
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
//        LocalDateTime dateTime = LocalDateTime.parse(value.toString(), formatter);
//        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
//        Date result = Date.from(instant);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATETIME_FORMAT);
        Date result = DateTime.parse(value.toString(), formatter).toDate();
        return result;
    }

    @Override
    Date[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        if (Date[].class.isAssignableFrom(arrayValues.getClass())) {
            return (Date[]) arrayValues;
        }
        List<Date> values = Lists.newArrayList();

        for (Date value : (ArrayList<Date>) arrayValues) {
            values.add(parseType(value));
        }

        return values.toArray(new Date[]{});
    }
}
