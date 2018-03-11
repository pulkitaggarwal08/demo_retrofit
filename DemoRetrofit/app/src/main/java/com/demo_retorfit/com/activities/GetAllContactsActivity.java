package com.demo_retorfit.com.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.adapter.GetContactsAdapter;
import com.demo_retorfit.com.config.App;
import com.demo_retorfit.com.config.AppConstant;
import com.demo_retorfit.com.entity.Contacts;
import com.demo_retorfit.com.utils.Utils;
import com.demo_retorfit.com.wrapper.RestCallback;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;

public class GetAllContactsActivity extends AppCompatActivity {

    private RecyclerView rv_get_all_contacts;
    private GetContactsAdapter getContactsAdapter;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_contacts);

        activity = GetAllContactsActivity.this;

        findIds();
        init();

    }

    private void findIds() {
        rv_get_all_contacts = findViewById(R.id.rv_get_all_contacts);
    }

    private void init() {

        rv_get_all_contacts.setLayoutManager(new LinearLayoutManager(App.getAppContext()));

        Map<String, String> map = new HashMap<>();
        map.put("", "");
        App.getApiHelper().getRestApiService(false).getContactsInformation(map)
                .enqueue(new RestCallback<Map>() {
                    @Override
                    public void onSuccess(Map map, Response response) {
                        ArrayList<Contacts> arrayList;
                        ArrayList<Contacts> contactsArrayList = new ArrayList<>();

                        LinkedTreeMap treeMap = ((LinkedTreeMap) response.body());
                        arrayList = ((ArrayList) treeMap.get(AppConstant.CONTACTS));

                        for (int i = 0; i < arrayList.size(); i++) {

                            String id = ((LinkedTreeMap) ((ArrayList) treeMap.get(AppConstant.CONTACTS)).get(i)).get("id").toString();
                            String name = ((LinkedTreeMap) ((ArrayList) treeMap.get(AppConstant.CONTACTS)).get(i)).get("name").toString();
                            String email = ((LinkedTreeMap) ((ArrayList) treeMap.get(AppConstant.CONTACTS)).get(i)).get("email").toString();
                            String address = ((LinkedTreeMap) ((ArrayList) treeMap.get(AppConstant.CONTACTS)).get(i)).get("address").toString();
                            String gender = ((LinkedTreeMap) ((ArrayList) treeMap.get(AppConstant.CONTACTS)).get(i)).get("gender").toString();

                            contactsArrayList.add(new Contacts(id, name, email, address, gender, null));
                        }
                        getContactsAdapter = new GetContactsAdapter(getApplicationContext(), contactsArrayList, new GetContactsAdapter.OnClickContactListener() {
                            @Override
                            public void onClickContact(int position, Contacts contacts) {
                                // Todo: get click here
                            }
                        });
                        rv_get_all_contacts.setAdapter(getContactsAdapter);
                    }

                    @Override
                    public void onFailure(String error) {
//                        Utils.generalOkAlert(error, activity);
                        Utils.generalOkAlert("Please set Base Url", activity);
                    }
                });
    }


}