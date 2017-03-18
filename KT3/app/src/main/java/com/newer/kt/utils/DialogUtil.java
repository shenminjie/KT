package com.newer.kt.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2016/8/23.
 */
public class DialogUtil {
    /**
     * 显示提示
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showAlert(Context context, String title, String message, String btnText, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNegativeButton(btnText, onClickListener).show();
    }

    /**
     * 显示警示
     *
     * @param context
     * @param title
     * @param message
     */
    public static void showAlert(Context context, String title, String message, String okayBtnText, String cancelBtnText, DialogInterface.OnClickListener onOkayBtnClickListener, DialogInterface.OnClickListener onCancelBtnClickListener) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setPositiveButton(okayBtnText, onOkayBtnClickListener).setNegativeButton(cancelBtnText, onCancelBtnClickListener).show();
    }

    public static void show(Dialog dialog){
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    public static void dismiss(Dialog dialog){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
