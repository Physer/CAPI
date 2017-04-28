package com.valtech.amsterdam.valtechapipoc.service;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by jaspe on 11-4-2017.
 */

public class AsyncCommandExecutor<TResult> extends AsyncTask<Command<TResult>, Void, TResult> {
    private TaskListener<TResult> mListener;

    public AsyncCommandExecutor(TaskListener<TResult> mListener) {
        this.mListener = mListener;
    }

    @Override
    protected TResult doInBackground(Command<TResult>... params) {
        try {
            TResult result = params[0].execute();
            return result;
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(TResult result) {
        super.onPostExecute(result);
        try {
            mListener.onComplete(result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
