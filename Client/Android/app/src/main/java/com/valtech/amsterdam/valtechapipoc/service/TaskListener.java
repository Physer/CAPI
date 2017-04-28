package com.valtech.amsterdam.valtechapipoc.service;

/**
 * Created by jaspe on 8-4-2017.
 */

public interface TaskListener<TResult> {
    void onComplete(TResult result) throws NoSuchMethodException;
}
