package com.valtech.amsterdam.valtechapipoc.service;

import com.valtech.amsterdam.valtechapipoc.model.BaseModel;
import com.valtech.amsterdam.valtechapipoc.service.loader.ModelLoader;

import java.util.List;

/**
 * Created by jaspe on 15-4-2017.
 */

public class LoadListCommand<TModel extends BaseModel> implements Command<List<TModel>> {
    private ModelLoader mModelLoader;

    public LoadListCommand(ModelLoader modelLoader) {
        mModelLoader = modelLoader;
    }

    @Override
    public List<TModel> execute() {
        return mModelLoader.getList();
    }
}

