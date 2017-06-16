package com.valtech.amsterdam.valtechapipoc.ui;

import com.jaspervz.www.recyclist.LoadListCommand;
import com.jaspervz.www.recyclist.Recyclist;
import com.jaspervz.www.recyclist.loader.ModelLoader;
import com.jaspervz.www.recyclist.loader.implementation.network.BufferedStreamContentReader;
import com.jaspervz.www.recyclist.loader.implementation.network.GsonDesynchronizer;
import com.jaspervz.www.recyclist.loader.implementation.network.NetworkModelLoader;
import com.valtech.amsterdam.valtechapipoc.model.Product;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jasper.van.zijp on 16-6-2017.
 */

@Module
public class RecyclistModule {
    @Provides
    @Singleton
    Recyclist<Product> getProductRecyclist(LoadListCommand<Product> loadListCommand){
        return new Recyclist<>(loadListCommand);
    }

    @Provides
    @Singleton
    LoadListCommand<Product> getProductLoadListCommand(ModelLoader<Product> modelLoader) {
        return new LoadListCommand<>(modelLoader);
    }

    @Provides
    @Singleton
    ModelLoader<Product> getProductModelLoader(@Named("apiUrl") String serverUrl) {
        return new NetworkModelLoader<>(
                new BufferedStreamContentReader(),
                new GsonDesynchronizer<>(Product.class),
                Product.class,
                serverUrl);
    }

    @Provides
    @Named("apiUrl")
    String provideApiUrl() {
        return "http://dev-capi.azurewebsites.net/api/";
    }
}
