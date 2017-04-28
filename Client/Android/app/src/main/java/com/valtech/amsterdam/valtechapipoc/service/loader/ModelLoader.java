package com.valtech.amsterdam.valtechapipoc.service.loader;

import com.valtech.amsterdam.valtechapipoc.model.BaseModel;

import java.util.List;

/**
 * Created by jaspe on 17-4-2017.
 */

public interface ModelLoader<TModel extends BaseModel, TKey> {
    TModel load(TKey key);
    List<TModel> getList();
}
