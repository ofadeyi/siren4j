package com.google.code.siren4j.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class ShortFieldValueContext extends AbstractFieldValueContext {

    ShortFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static ShortFieldValueContext newInstance() {
        return new ShortFieldValueContext(IntegerFieldValueContext.newInstance(), short[].class, Short[].class);
    }

    @Override
    boolean canProcess(Class<?> fieldType) {
        if ((Short.class.equals(fieldType)) ||
                (short.class.equals(fieldType))) {
            return true;
        }
        return false;
    }

    @Override
    Short parseType(Object value) {
        if (value == null) {
            return null;
        }
        return Short.valueOf(value.toString());
    }

    @Override
    Short[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        List<Short> values = Lists.newArrayList();

        for (Short value : ((Short[]) arrayValues)) {
            values.add(parseType(value));
        }


        return values.toArray(new Short[]{});
    }
}
