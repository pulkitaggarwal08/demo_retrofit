package com.demo_retorfit.com.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.activities.DashboardActivity;
import com.demo_retorfit.com.activities.MainActivity;
import com.demo_retorfit.com.config.App;
import com.demo_retorfit.com.config.AppConstant;
import com.demo_retorfit.com.config.AppPreference;
import com.demo_retorfit.com.eventbusmodels.TokenRequest;
import com.demo_retorfit.com.eventbusmodels.TokenResponse;
import com.demo_retorfit.com.utils.Utils;
import com.demo_retorfit.com.wrapper.RestCallback;

import retrofit2.Response;

public class ProfileInformationActivity extends AppCompatActivity {

    public static final String EXTRA_IS_SIGNUP = "is_sign_up";
    public static final String EXTRA_OTP = "otp";

    private String phoneNum;
    public boolean isSignUp;
    private String otp;

    private Activity activity;

    public static Intent getMyIntent(Context context, boolean isSignUp, String otp) {
        Intent intent = new Intent(context, ProfileInformationActivity.class);
        intent.putExtra(ProfileInformationActivity.EXTRA_IS_SIGNUP, isSignUp);
        intent.putExtra(ProfileInformationActivity.EXTRA_OTP, otp);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);

        activity = ProfileInformationActivity.this;

        findIds();
        init();
    }

    private void findIds() {


    }

    private void init() {

        phoneNum = App.getAppPreference().getSavedString(AppPreference.KEY_PHONE_NUM, null);
        if (getIntent().getExtras() != null) {
            isSignUp = getIntent().getBooleanExtra(EXTRA_IS_SIGNUP, false);
            otp = getIntent().getStringExtra(EXTRA_OTP);
        }
        getToken();

    }

    private void getToken() {
        //Todo: show Progress Bar
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.username = "+91" + phoneNum;
        tokenRequest.password = otp;

        App.getApiHelper().getRestApiService(false).generateToken(AppConstant.ACCOUNT_ID, tokenRequest)
                .enqueue(new RestCallback<TokenResponse>() {
                    @Override
                    public void onSuccess(TokenResponse tokenResponse, Response response) {
                        //Todo: hide progress bar
                        App.getAppPreference().saveString(AppPreference.KEY_USER_TOKEN, tokenResponse.authorization);
                        App.getAppPreference().saveString(AppPreference.KEY_USER_REFRESH_TOKEN, tokenResponse.refresh_token);

                        gotoMain();
                    }

                    @Override
                    public void onFailure(String error) {
                        //Todo: hide progress bar
                        Utils.generalOkAlert(error, activity);
                    }
                });

    }

    private void gotoMain() {

        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
        finish();
    }


}
