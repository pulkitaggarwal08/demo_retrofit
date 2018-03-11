package com.demo_retorfit.com.wrapper;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.utils.Utils;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by pulkit on 10/3/18.
 */
public abstract class RestCallback<T> implements Callback<T> {

    public abstract void onSuccess(T t, Response response);
    public abstract void onFailure(String error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int code = response.code();
        String message = response.message();
        if (code >= 200 && code < 300) {
            onSuccess(response.body(), response);
        } else if (code == 401) {
            if (!Utils.isEmpty(message)) {
                onFailure(message);
            } else {
                onFailure(Utils.getStringFromRes(R.string.nw_error_auth));
            }
        } else if (code >= 400 && code < 500) {
            if (!Utils.isEmpty(message)) {
                onFailure(message);
            } else {
                onFailure(Utils.getStringFromRes(R.string.nw_error_unable_to_process));
            }
        } else if (code >= 500 && code < 600) {
            if (!Utils.isEmpty(message)) {
                onFailure(message);
            } else {
                onFailure(Utils.getStringFromRes(R.string.nw_error_server_error));
            }
        } else {
            onFailure(Utils.getStringFromRes(R.string.nw_error_unexpected));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (throwable instanceof IOException) {
            onFailure(Utils.getStringFromRes(R.string.nw_error_network_error));
        } else if (throwable instanceof IllegalArgumentException) {
            String msg = throwable.getMessage();
            if (Utils.isEmpty(msg)) {
                onFailure(throwable.getMessage());
            } else {
                onFailure(Utils.getStringFromRes(R.string.nw_error_something_went_wrong));
            }
        } else {
            onFailure(Utils.getStringFromRes(R.string.nw_error_unexpected));
        }
    }

}
