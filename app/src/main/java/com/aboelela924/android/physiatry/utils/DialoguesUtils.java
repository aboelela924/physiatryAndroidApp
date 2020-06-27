package com.aboelela924.android.physiatry.utils;

import android.app.Activity;
import android.content.Context;

import com.aboelela924.android.physiatry.R;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;


public class DialoguesUtils {

    public static void showErrorMessage(Activity a, String title, String message){

        SweetAlertDialog dialog = new SweetAlertDialog(a, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(message);

        dialog.show();

    }

    public static void showSuccessMessage(Activity a, String title, String message){

        SweetAlertDialog dialog = new SweetAlertDialog(a, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message);

        dialog.show();


    }

}
