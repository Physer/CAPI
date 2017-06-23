package com.valtech.amsterdam.valtechapipoc.platform.injection;

/**
 * Created by jasper.van.zijp on 16-6-2017.
 */

import com.valtech.amsterdam.valtechapipoc.ui.MainActivity;
import com.valtech.amsterdam.valtechapipoc.ui.RecyclistModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = { RecyclistModule.class })
public interface InjectionComponent {

    // provide the dependency for dependent components
    // (not needed for single-component setups)

    // allow to inject into our Main class
    // method name not important
    void inject(MainActivity main);
}