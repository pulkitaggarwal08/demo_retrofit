package com.demo_retorfit.com.registration;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.config.App;
import com.demo_retorfit.com.config.AppConstant;
import com.demo_retorfit.com.config.AppPreference;
import com.demo_retorfit.com.utils.Utils;
import com.demo_retorfit.com.wrapper.RestCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class CheckMobileNumbersActivity extends AppCompatActivity {

    private Button btnCheck;
    private EditText etGetNumber;
    private String strGetNumber;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mobile_numbers);

        activity = CheckMobileNumbersActivity.this;

        findIds();
        init();
    }

    private void findIds() {
        btnCheck = findViewById(R.id.btn_check);
        etGetNumber = findViewById(R.id.et_get_number);
    }

    private void init() {
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isEmpty(Utils.getTextFromView(etGetNumber))) {
                    Utils.generalOkAlert(getString(R.string.validPhone), activity);

                } else {
                    getMobileResponse();
                }
            }
        });
    }

    private void getMobileResponse() {
        final String mobileNumber = Utils.getTextFromView(etGetNumber);
        Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", mobileNumber);

        // save phone num
        App.getAppPreference().saveString(AppPreference.KEY_PHONE_NUM, mobileNumber);

        //Todo: show progress bar
        App.getApiHelper().getRestApiService(false).registerMobile(map)
                .enqueue(new RestCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody, Response response) {
                        //Todo: hide progress bar
                        try {
                            String getResponse = responseBody.string();

                            Intent intent = new Intent(getApplicationContext(), VerifyCodeActivity.class);
                            intent.putExtra("mobileNumber", mobileNumber);
                            startActivity(intent);
                            finish();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        //Todo: hide progress bar
                        Utils.generalOkAlert(error, activity);
                    }
                });
    }
}
