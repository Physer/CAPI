package com.valtech.amsterdam.valtechapipoc.service.loader;

import com.valtech.amsterdam.valtechapipoc.model.BaseModel;

import java.io.IOException;
import java.util.List;

/**
 * A loader of models of type TModel, and with a key TKey
 */

public interface ModelLoader<TModel extends BaseModel, TKey> {
    TModel load(TKey key);
    List<TModel> getList() throws IOException;
}
