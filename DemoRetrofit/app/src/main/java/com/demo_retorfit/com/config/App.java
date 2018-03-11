package com.demo_retorfit.com.config;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.demo_retorfit.com.api.ApiHelper;

/**
 * Created by Pulkit on 10-Jun-17.
 */

public class App extends Application {
    private static App instance;
    private static ApiHelper apiHelper;
    private static AppPreference appPreference;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
        instance = this;
//        apiHelper = ApiHelper.init(instance);
        apiHelper = new ApiHelper();
        appPreference = AppPreference.init(instance);
    }

    public static App getAppContext(){
        return instance;
    }

    public static ApiHelper getApiHelper(){
        return apiHelper;
    }

    public static AppPreference getAppPreference(){
        return appPreference;
    }

}
