package com.valtech.amsterdam.valtechapipoc.model.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jaspe on 11-4-2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInfo {
    String methodName();
}
