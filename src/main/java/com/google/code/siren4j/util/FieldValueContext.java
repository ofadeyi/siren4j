package com.google.code.siren4j.util;

import com.google.common.base.Optional;

/**
 * Created by ofadeyi on 28/05/15.
 */
public interface FieldValueContext {
    Optional<Object> get(Class<?> fieldType, Object value);
    FieldValueContext next();
}
