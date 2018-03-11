package com.demo_retorfit.com.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.TextView;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.config.App;

/**
 * Created by pulkit on 10/3/18.
 */

public class Utils {

    public static boolean isEmpty(String data) {
        if (TextUtils.isEmpty(data)) {
            return true;
        }
        return false;
    }

    // get string from resource
    public static String getStringFromRes(@StringRes int res) {
        return App.getAppContext().getResources().getString(res);
    }

    // gets the text from edittext and textview
    public static String getTextFromView(TextView editText) {
        return editText.getText().toString().trim();
    }

    // error dialog
    public static void generalOkAlert(String message, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setTitle(getStringFromRes(R.string.app_name))
                .setCancelable(false)
                .setPositiveButton(getStringFromRes(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }

}
