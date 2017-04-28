package com.valtech.amsterdam.valtechapipoc.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jasper.van.zijp on 14-4-2017.
 */

public abstract class BaseModel<TKeyType> {
    @SerializedName("Identifier") private TKeyType mKey;

    protected BaseModel(TKeyType mKeyType) {
        mKey = mKeyType;
    }

    public TKeyType getKey() {
        return mKey;
    }
}
