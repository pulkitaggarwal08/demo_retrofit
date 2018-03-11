package com.demo_retorfit.com.api;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.config.App;
import com.demo_retorfit.com.config.AppConstant;
import com.demo_retorfit.com.utils.Utils;
import com.demo_retorfit.com.wrapper.RestCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pulkit on 10/3/18.
 */

public class ApiHelper {

    private static App app;
    private static ApiHelper apiHelper;
    private ApiService apiService;
    private ApiService apiTokenService;

    public ApiHelper() {
        apiService = getRetrofit(false).create(ApiService.class);
        apiTokenService = getRetrofit(false).create(ApiService.class);
    }

    public static ApiHelper init(App app) {
        if (apiHelper == null) {
            apiHelper = new ApiHelper();
//            apiHelper.initApiService();
            setApp(app);
        }
        return apiHelper;
    }

    private Retrofit getRetrofit(boolean isToken) {
        Gson gson = new GsonBuilder()
//                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                // excludes the fields without annotation
                .serializeNulls()
                .create();
        return new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
//                .client(getOkHttpClient(isToken))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

//    private void initApiService() {
////        App.getPreferences().saveString(AppConstant.IS_LOGGED_IN, "false");
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConstant.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        apiService = retrofit.create(ApiService.class);
//    }

    private static void setApp(App app) {
        ApiHelper.app = app;
    }

    public ApiService getRestApiService(boolean isToken) {
        if (isToken) {
            return apiTokenService;
        } else {
            return apiService;
        }
    }

}




