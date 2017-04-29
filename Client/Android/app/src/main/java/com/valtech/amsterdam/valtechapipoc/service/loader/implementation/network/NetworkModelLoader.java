package com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network;

import android.util.Log;

import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.BaseModel;
import com.valtech.amsterdam.valtechapipoc.model.annotation.ApiInfo;
import com.valtech.amsterdam.valtechapipoc.platform.PreferenceManager;
import com.valtech.amsterdam.valtechapipoc.service.loader.ModelLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * A Model loader which loads models from a Network connection
 */

public class NetworkModelLoader<TModel extends BaseModel> implements ModelLoader<TModel, UUID> {
    private UrlContentReader mUrlContentReader;
    private Desynchronizer<TModel> mDesynchronizer;
    private PreferenceManager mPreferenceManager;
    private Class<TModel> mClassType; //Need to store the class variable because getting the class of a generic type is not possible in compile time

    public NetworkModelLoader(UrlContentReader mUrlContentReader, Desynchronizer<TModel> mDesynchronizer, PreferenceManager preferenceManager, Class<TModel> classType) {
        this.mUrlContentReader = mUrlContentReader;
        this.mDesynchronizer = mDesynchronizer;
        this.mPreferenceManager = preferenceManager;
        mClassType = classType;
    }


    @Override
    public TModel load(UUID uuid) {
        return null;
    }

    @Override
    public List<TModel> getList() throws IOException {
        ApiInfo annotation = mClassType.getAnnotation(ApiInfo.class);
        URL url = new URL(mPreferenceManager.GetValue(R.string.api_base_location) + annotation.methodName());

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String content = mUrlContentReader.readContent(urlConnection);
        return mDesynchronizer.getList(content);
    }
}
