package com.valtech.amsterdam.valtechapipoc.service;

/**
 * Describes TaskListener interface
 */

public interface TaskListener<TResult> {
    void onComplete(TResult result);
    void onError(Exception exception);
}
