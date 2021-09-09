package com.chersoft.simplenotes.data.Service;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class Service {
    public static final String BASE_URL = "http://192.168.1.10:8080/";

    private final Retrofit retrofit;
    private final ServiceAPI serviceAPI;

    @Inject
    public Service(Retrofit retrofit, ServiceAPI serviceAPI){
        this.retrofit = retrofit;
        this.serviceAPI = serviceAPI;
    }

    public ServiceAPI getServiceAPI() {
        return serviceAPI;
    }
}
