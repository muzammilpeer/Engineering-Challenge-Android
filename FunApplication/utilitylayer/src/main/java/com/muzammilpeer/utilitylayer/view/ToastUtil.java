package com.muzammilpeer.utilitylayer.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.muzammilpeer.utilitylayer.enums.ToastEnums;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class ToastUtil {

    private ToastUtil() {
        throw new AssertionError();
    }

    public static void showToast(Context context, int stringResId) {
        showToast(context, stringResId, ToastEnums.SHORT);
    }

    public static void showToast(Context context, int stringResId, ToastEnums duration) {
        showToast(context, context.getString(stringResId), duration);
    }

    public static void showToast(Context context, String text) {
        showToast(context, text, ToastEnums.SHORT);
    }

    public static void showToast(Context context, String text, ToastEnums duration) {
        Toast toast = Toast.makeText(context, text,
                (duration == ToastEnums.SHORT ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
