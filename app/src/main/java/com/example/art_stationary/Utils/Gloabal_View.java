package com.example.art_stationary.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.art_stationary.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Gloabal_View {
    public static final int CODE_UNAUTHORISED = 401;
    public static final int CODE_SUCCESSFULL = 200;
    public static final int CODE_BAD_REQUEST = 400;
    public static final String VERTICAL="1";
    public static final String HORIZONTAL="2";


    public static void changeFragment(Context context, Fragment fragment) {

        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
             fragmentManager.beginTransaction().replace(R.id.flFragment,fragment).addToBackStack(null).commitAllowingStateLoss();

    }

    public static boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] convertVideo(String path) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(new File(path));

        byte[] buf = new byte[480];
        int n;
        while (-1 != (n = fis.read(buf)))
            baos.write(buf, 0, n);

        return baos.toByteArray();
    }
   /* public static Dialog showProgress(Context context) {
        Dialog dialog = null;
        if (context != null) {
            dialog = new Dialog(context);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.global_progress_bar);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.gravity = Gravity.CENTER_VERTICAL;
            }
        }
        return dialog;
    }*/



   /* public static boolean isNetWorkAvailable(Context context) {
        boolean isAvailable=false;
        if (context!=null) {
            ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = conn.getActiveNetworkInfo();
            isAvailable= activeNetwork != null && activeNetwork.isConnected();
        }
        return isAvailable;


    }*/

    public static RecyclerView.LayoutManager layoutManager(Context context, String direction, int gridCount){
        LinearLayoutManager layoutManager;
        if (direction.equals(VERTICAL)){
            layoutManager= new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        }else if (direction.equals(HORIZONTAL)){
            layoutManager= new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        }else {
            layoutManager= new GridLayoutManager(context, gridCount);
        }
        return layoutManager;
    }


    public static void statusbar(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity,R.color.black));
    }

    public static Dialog showProgress(Context context) {
        Dialog dialog = null;
        if (context != null) {
            dialog = new Dialog(context);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.global_progress_bar);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.gravity = Gravity.CENTER_VERTICAL;
            }
        }
        return dialog;
    }

/*
    public static void showMessage(View view, String message, int length){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.orange));
        snackbar.show();
    }
*/


}
