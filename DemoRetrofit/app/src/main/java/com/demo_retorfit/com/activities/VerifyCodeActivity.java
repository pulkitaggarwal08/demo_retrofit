package com.demo_retorfit.com.activities;

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

        final String phoneNum = getIntent().getStringExtra("mobileNumber");
        String otp = Utils.getTextFromView(etConfirmCode);

        final Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", phoneNum);
        map.put("otp", otp);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getApiHelper().getRestApiService(false).otpVerification(map)
                        .enqueue(new RestCallback<JSONObject>() {
                            @Override
                            public void onSuccess(JSONObject jsonObject, Response response) {
                                etConfirmCode.setText("");
                                getUserProfile(phoneNum);
                            }

                            @Override
                            public void onFailure(String error) {
                                etConfirmCode.setText("");
                                Utils.generalOkAlert(error, activity);
                            }
                        });
            }
        });
    }

    private void getUserProfile(String phoneNum) {
        App.getApiHelper().getRestApiService(false).getProfile(AppConstant.ACCOUNT_ID, phoneNum)
                .enqueue(new RestCallback<ProfileEntity>() {
                    @Override
                    public void onSuccess(ProfileEntity profileEntity, Response response) {
                        Utils.generalOkAlert(profileEntity.first_name, activity);
                    }

                    @Override
                    public void onFailure(String error) {
                        Utils.generalOkAlert(Utils.getStringFromRes(R.string.validWentWrong), activity);
                    }
                });

    }

}
