package com.google.code.siren4j.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class LongFieldValueContext extends AbstractFieldValueContext {

    LongFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static LongFieldValueContext newInstance() {
        return new LongFieldValueContext(FloatFieldValueContext.newInstance(), long[].class, Long[].class);
    }

    @Override
    boolean canProcess(Class<?> fieldType) {
        if ((Long.class.equals(fieldType)) ||
                (long.class.equals(fieldType))) {
            return true;
        }
        return false;
    }

    @Override
    Long parseType(Object value) {
        if (value == null) {
            return null;
        }
        return Long.valueOf(value.toString());
    }

    @Override
    Long[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        List<Long> values = Lists.newArrayList();

        for (Long value : ((Long[]) arrayValues)) {
            values.add(parseType(value));
        }

        return values.toArray(new Long[]{});
    }
}
