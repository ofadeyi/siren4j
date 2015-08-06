package com.google.code.siren4j.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class StringFieldValueContext extends  AbstractFieldValueContext {

    StringFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static StringFieldValueContext newInstance() {
        return new StringFieldValueContext(DateFieldValueContext.newInstance(), String[].class);
    }
    @Override
    boolean canProcess(Class<?> fieldType) {
        if (String.class.equals(fieldType)) {
            return true;
        }
        return false;
    }
    @Override
    String parseType(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    String[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        if (String[].class.isAssignableFrom(arrayValues.getClass())) {
            return (String[]) arrayValues;
        }
        List<String> values = Lists.newArrayList();

        for (Object value : ((ArrayList<String>)arrayValues) ) {
            values.add(parseType( value));
        }


        return values.toArray(new String[]{});
    }
}
