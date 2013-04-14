package com.scheakur.orehn;

import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;

import javax.ws.rs.ext.Provider;

@Provider
public class DataStoreProvider extends SingletonTypeInjectableProvider<Inject, DataStore> {

    public DataStoreProvider() {
        super(DataStore.class, new DataStore());
    }

}
