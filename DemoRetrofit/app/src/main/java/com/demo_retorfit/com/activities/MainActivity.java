package com.demo_retorfit.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.registration.CheckMobileNumbersActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGetAllContacts, btnCheckMobileNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findIds();
        init();

    }

    private void findIds() {
        btnGetAllContacts = findViewById(R.id.btn_get_all_contacts);
        btnCheckMobileNumbers =  findViewById(R.id.btn_check_mobile_number);
    }

    private void init() {

        btnGetAllContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetAllContactsActivity.class);
                startActivity(intent);
            }
        });

        btnCheckMobileNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckMobileNumbersActivity.class);
                startActivity(intent);
            }
        });

    }

}
