package com.valtech.amsterdam.valtechapipoc.service;

/**
 * Created by jaspe on 11-4-2017.
 */

public interface Command<TResult> {
    TResult execute();
}
