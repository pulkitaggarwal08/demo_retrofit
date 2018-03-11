package com.demo_retorfit.com.api;

import com.demo_retorfit.com.entity.ProfileEntity;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by pulkit on 10/3/18.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("phone-numbers/register-number")
    Call<ResponseBody> registerMobile(@FieldMap Map<String, String> stringMap);

    @FormUrlEncoded
    @POST("contacts/")
    Call<Map> getContactsInformation(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("phone-numbers/verify-number")
    Call<JSONObject> otpVerification(@FieldMap Map<String, String> stringMap);

    // User information
    @GET("accounts/{accountId}/{phoneNum}")
    Call<ProfileEntity> getProfile(@Path("accountId") String accountId, @Path("phoneNum") String phoneNum);


}
