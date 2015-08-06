package com.google.code.siren4j.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class FloatFieldValueContext extends AbstractFieldValueContext {

    FloatFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static FloatFieldValueContext newInstance() {
        return new FloatFieldValueContext(DoubleFieldValueContext.newInstance(), float[].class, Float[]
                .class);
    }
    @Override
    boolean canProcess(Class<?> fieldType) {
        if ((Float.class.equals(fieldType)) ||
                (float.class.equals(fieldType))) {
            return true;
        }
        return false;
    }
    @Override
    Float parseType(Object value) {
        if (value == null) {
            return null;
        }
        return Float.valueOf(value.toString());
    }

    @Override
    Float[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        List<Float> values = Lists.newArrayList();

        for (Float value : (Float[])arrayValues) {
            values.add(parseType(value));
        }

        return values.toArray(new Float[]{});
    }
}
