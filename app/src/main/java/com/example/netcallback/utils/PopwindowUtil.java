package com.example.netcallback.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.netcallback.R;
import com.example.netcallback.ui.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class PopwindowUtil {
    private volatile static PopwindowUtil mInstance;
    private int popDialoglayoutId = R.layout.cancel_pop_layout;
    private PopupWindow popupWindow;


    public static PopwindowUtil getmInstance() {
        if (mInstance == null) {
            synchronized (PopwindowUtil.class) {
                if (mInstance == null) {
                    mInstance = new PopwindowUtil();
                }
            }
        }
        return mInstance;
    }

    private PopwindowUtil() {
    }

    public void showCancelPopupWindow(final Context context, int fatherLayoutId) {
        showCancelPopupWindow(context, fatherLayoutId, 1.0f,1000);

    }

    /**
     * @param context
     * @param fatherLayoutId
     * @param backAlpha
     */
    private void showCancelPopupWindow(final Context context, int fatherLayoutId, float backAlpha, int time) {
        setBackGroundAlpha(backAlpha, context);
        View view = LayoutInflater.from(context).inflate(popDialoglayoutId, null);
        popupWindow = new PopupWindow(view,150, 150, true);
        popupWindow.setContentView(view);
        View rootView = LayoutInflater.from(context).inflate(fatherLayoutId, null);
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
        final Timer timer = new Timer();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    dismissPopWindow(popupWindow);
                }
            }
        };
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackGroundAlpha(1.0f, context);
                timer.cancel();

            }
        });
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, time);


    }

    private void dismissPopWindow(PopupWindow popupWindow) {
        if (popupWindow != null) popupWindow.dismiss();
    }

    private void setBackGroundAlpha(float alpha, Context context) {

        WindowManager.LayoutParams layoutParams = ((Activity) context).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) context).getWindow().setAttributes(layoutParams);
    }


}
