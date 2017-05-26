package com.valtech.amsterdam.valtechapipoc.service;

import android.view.View;

import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.BaseModel;
import com.valtech.amsterdam.valtechapipoc.model.Product;
import com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network.BufferedStreamContentReader;
import com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network.GsonDesynchronizer;
import com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network.NetworkModelLoader;
import com.valtech.amsterdam.valtechapipoc.ui.ProductAdapter;

import java.util.List;

/**
 * Created by jasper.van.zijp on 26-5-2017.
 */

public class Recyclist<TResult extends BaseModel> {
    private LoadListCommand<TResult> mTask;
    private TaskListener<List<TResult>> mListener;

    public void startBind() {
        mTask = new LoadListCommand<>(
                new NetworkModelLoader<>(
                        new BufferedStreamContentReader(),
                        new GsonDesynchronizer<>(Product.class),
                        Product.class,
                        getString(R.string.api_base_location)));

        AsyncCommandExecutor<List<Product>> productsExecutor = new AsyncCommandExecutor<>(new TaskListener<List<Product>>() {
            @Override
            public void onComplete(List<Product> products) {
                onLoadComplete(products);
            }

            @Override
            public void onError(Exception exception) {
                onLoadError();
            }
        });

        taskExecutor.execute(mTask);
    }

    private void onLoadComplete(List<Product> products) {
        mTask = null;
        mProgressBar.setVisibility(View.GONE);
        ProductAdapter adapter = new ProductAdapter(result, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
