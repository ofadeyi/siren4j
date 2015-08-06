package com.google.code.siren4j.util;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class IntegerFieldValueContext extends  AbstractFieldValueContext {

    IntegerFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static IntegerFieldValueContext newInstance() {
        return new IntegerFieldValueContext(LongFieldValueContext.newInstance(),int[].class, Integer[].class);
    }

    @Override
    boolean canProcess(Class<?> fieldType) {
        if ((Integer.class.equals(fieldType)) ||
                (int.class.equals(fieldType))) {
            return true;
        }
        return false;
    }
    @Override
    Integer parseType(Object value) {
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value.toString());
    }

    @Override
    Integer[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        List<Integer> values = Lists.newArrayList();

        for (Integer value : ((Integer[])arrayValues)){
            values.add(parseType( value));
        }

        return values.toArray(new Integer[]{});
    }
}
