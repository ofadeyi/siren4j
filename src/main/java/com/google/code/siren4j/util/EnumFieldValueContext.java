package com.google.code.siren4j.util;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class EnumFieldValueContext extends AbstractFieldValueContext {

    private Class fieldType;

    EnumFieldValueContext(FieldValueContext nextContext) {
        super( nextContext);
    }

    public static EnumFieldValueContext newInstance() {
        return new EnumFieldValueContext(ObjectFieldValueContext.newInstance());
    }

    @Override
    boolean canProcess(Class<?> fieldType) {
        if (Enum.class.isAssignableFrom(fieldType)) {
            this.fieldType = fieldType;
            return true;
        }
        return false;
    }

    @Override
    Enum parseType(Object value) {
        if (value == null) {
            return null;
        }
        if (Enum.class.isAssignableFrom(value.getClass())) {
            return (Enum) value;
        }
        return Enum.valueOf(fieldType, (String)value);
    }

    @Override
    Enum[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        List<Enum> values = Lists.newArrayList();

        for (Enum value : (Enum[])arrayValues){
            values.add(parseType(value));
        }
        return values.toArray(new Enum[]{});
    }
}
