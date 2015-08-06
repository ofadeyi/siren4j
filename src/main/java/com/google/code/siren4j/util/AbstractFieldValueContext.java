package com.google.code.siren4j.util;

import com.google.common.base.Optional;

import java.util.Arrays;

/**
 * Created by ofadeyi on 28/05/15.
 */
public abstract class AbstractFieldValueContext implements FieldValueContext {
    private FieldValueContext nextContext;
    private boolean arrayAllowed = false;
    private Object[] arrayTypes;

    protected AbstractFieldValueContext(FieldValueContext nextContext, Object... arrayTypes) {
        this.nextContext = nextContext;
        if (arrayTypes != null){
            this.arrayTypes = arrayTypes;
            arrayAllowed = true;
        }
    }

    abstract <T> T parseType(Object value);
    abstract <T> T parseArray(Object arrayValues);
    abstract boolean canProcess(Class<?> fieldType);

    public Optional<Object> get(Class<?> fieldType, Object value) {
        if (canProcess(fieldType) ||
                (this.nextContext == null)){
            return Optional.fromNullable(parseType(value));
        }
        if ((arrayAllowed) && (Arrays.asList(arrayTypes).contains(fieldType))){
            return Optional.fromNullable(parseArray(value));
        }
        return next().get(fieldType, value);
    }

    public FieldValueContext next() {
        return nextContext;
    }
}
