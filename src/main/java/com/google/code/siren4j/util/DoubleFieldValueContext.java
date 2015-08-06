package com.google.code.siren4j.util;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class DoubleFieldValueContext extends  AbstractFieldValueContext {

    DoubleFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        super(nextContext, arrayTypes);
    }

    public static DoubleFieldValueContext newInstance() {
        return new DoubleFieldValueContext(StringFieldValueContext.newInstance(),double[].class,
                Double[].class);
    }
    @Override
    boolean canProcess(Class<?> fieldType) {
        if ((Double.class.equals(fieldType)) ||
                (double.class.equals(fieldType))) {
            return true;
        }
        return false;
    }
    @Override
    Double parseType(Object value) {
        if (value == null) {
            return null;
        }
        return Double.valueOf(value.toString());
    }

    @Override
    Double[] parseArray(Object arrayValues) {
        if (arrayValues == null) {
            return null;
        }
        List<Double> values = Lists.newArrayList();

        for (Double value : (Double[])arrayValues){
            values.add(parseType( value));
        }

        return values.toArray(new Double[]{});
    }
}
