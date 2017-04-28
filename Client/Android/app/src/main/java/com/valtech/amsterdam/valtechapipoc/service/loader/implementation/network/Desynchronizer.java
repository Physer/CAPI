package com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network;

import com.valtech.amsterdam.valtechapipoc.model.BaseModel;

import java.util.List;

/**
 * Created by jasper.van.zijp on 18-4-2017.
 */

public interface Desynchronizer<TModel extends BaseModel> {
    List<TModel> getList(String json);
}
