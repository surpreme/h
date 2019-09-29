package com.example.netcallback.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    /**
     * 如果有需要可以改成Util 封装为一个工具
     * <p>
     * 这里为什么没有封装为全局变量 如果得不到context会崩溃的
     * 而且可以避免toast显示过于频繁
     */
    private static Toast toast;

    public static void showToast(Context context, String string) {

        if (toast == null)
            toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        else
            toast.setText(string);

        toast.show();
    }


}
