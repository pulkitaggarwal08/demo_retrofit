package com.demo_retorfit.com.registration;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.config.App;
import com.demo_retorfit.com.config.AppConstant;
import com.demo_retorfit.com.entity.ProfileEntity;
import com.demo_retorfit.com.utils.Utils;
import com.demo_retorfit.com.wrapper.RestCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText etConfirmCode;
    private Button btnConfirm;

    private String phoneNum, otp;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        activity = VerifyCodeActivity.this;

        findIds();
        init();

    }

    private void findIds() {
        etConfirmCode = findViewById(R.id.etConfirmCode);
        btnConfirm = findViewById(R.id.btnConfirm);
    }
    private void init() {

        phoneNum = getIntent().getStringExtra("mobileNumber");
        otp = Utils.getTextFromView(etConfirmCode);

        final Map<String, String> map = new HashMap<>();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: show Progress Bar
                otp = Utils.getTextFromView(etConfirmCode);

                map.put("mobileNumber", phoneNum);
                map.put("otp", otp);

                App.getApiHelper().getRestApiService(false).otpVerification(map)
                        .enqueue(new RestCallback<JSONObject>() {
                            @Override
                            public void onSuccess(JSONObject jsonObject, Response response) {
                                //Todo: hide Progress Bar
                                etConfirmCode.setText("");
                                getUserProfile(phoneNum);
                            }

                            @Override
                            public void onFailure(String error) {
                                //Todo: hide Progress Bar
                                etConfirmCode.setText("");
                                Utils.generalOkAlert(error, activity);
                            }
                        });
            }
        });
    }

    private void getUserProfile(String phoneNum) {
        //Todo: show Progress Bar
        App.getApiHelper().getRestApiService(false).getProfile(AppConstant.ACCOUNT_ID, phoneNum)
                .enqueue(new RestCallback<ProfileEntity>() {
                    @Override
                    public void onSuccess(ProfileEntity getProfileResponse, Response response) {
                        //Todo: hide Progress Bar
                        if (getProfileResponse != null) {
                            //Todo: save data in database here
//                            PantepicApp.getDb().profileDao().save(getProfileResponse);

                            gotoProfileInfo(false);
                        } else {
                            Utils.generalOkAlert(getProfileResponse.first_name, activity);
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        //Todo: hide Progress Bar
                        if (error != null) {
                            gotoProfileInfo(true);
                        } else {
                            Utils.generalOkAlert(Utils.getStringFromRes(R.string.validWentWrong), activity);
                        }
                    }
                });

    }


    private void gotoProfileInfo(boolean isSignUp) {
        if (isSignUp) {
            //Todo: delete all data from database here
//            PantepicApp.getDb().profileDao().deleteTable();

        } else {
            startActivity(ProfileInformationActivity.getMyIntent(this, isSignUp, otp));
            finish();
        }
    }

}
