package com.valtech.amsterdam.valtechapipoc.platform;

import android.content.Context;

/**
 * Created by jaspe on 15-4-2017.
 */

public class StringResourcePreferenceManager implements PreferenceManager {
    private Context mContext;

    public StringResourcePreferenceManager(Context mContext) {
        this.mContext = mContext;
    }

    public String GetValue(int resId) {
        return mContext.getString(resId);
    }
}
