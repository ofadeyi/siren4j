package com.google.code.siren4j.util;

/**
 * Created by ofadeyi on 28/05/15.
 */
public class ObjectFieldValueContext extends AbstractFieldValueContext {

    ObjectFieldValueContext(FieldValueContext nextContext) {
        super( nextContext);
    }

    public static ObjectFieldValueContext newInstance() {
        return new ObjectFieldValueContext(null);
    }

    @Override
    boolean canProcess(Class<?> fieldType) {
        return true;
    }

    @Override
    Object parseType(Object value) {
        return value;
    }

    @Override
    Object[] parseArray(Object arrayValues) {
        return (Object[]) arrayValues;
    }
}
