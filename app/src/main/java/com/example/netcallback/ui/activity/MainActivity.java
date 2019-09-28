package com.example.netcallback.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.netcallback.R;
import com.example.netcallback.bean.GetJson;
import com.example.netcallback.callback.HttpCallback;
import com.example.netcallback.struct.FuctionNoParamNoResult;
import com.example.netcallback.struct.FuctionWithParamAndResult;
import com.example.netcallback.struct.FuctionWithParamOnly;
import com.example.netcallback.struct.FuctionWithResultOnly;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.fragment.BaseFragment;
import com.example.netcallback.ui.fragment.Fragment1;
import com.example.netcallback.ui.fragment.Fragment2;
import com.example.netcallback.ui.fragment.Fragment3;
import com.example.netcallback.utils.GlideImageLoader;
import com.example.netcallback.utils.HttpHelper;
import com.example.netcallback.utils.LogUtils;
import com.example.netcallback.utils.StatusBarUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String FIRST_APP_TAG = "firstLoading";
    private int recLen = 10;
    private Fragment1 messageFragment;
    private Fragment2 newsFragment;
    private Fragment3 settingFragment;
    private View messageLayout;
    private View newsLayout;
    private View settingLayout;
    private ImageView message_image;
    private ImageView news_image;
    private ImageView setting_image;
    private TextView message_text;
    private TextView news_text;
    private TextView setting_text;
    private FragmentManager fragmentManager;
    private int mFragmentTag_INDEX;
    protected String CODE_FRAGMENT_KEY;//key
    private static final String[] FRAGMENT_TAG = {"messageFragment", "newsFragment", "settingFragment"};


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        setContentView(R.layout.activity_main);
        initLoading(savedInstanceState);
        initViews();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 0 && messageFragment == null)
                messageFragment = (Fragment1) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 1 && newsFragment == null)
                newsFragment = (Fragment2) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 2 && settingFragment == null)
                settingFragment = (Fragment3) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            setTabSelection(savedInstanceState.getInt(CODE_FRAGMENT_KEY));
        } else
            setTabSelection(0);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CODE_FRAGMENT_KEY, mFragmentTag_INDEX);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        messageLayout = findViewById(R.id.message_layout);
        newsLayout = findViewById(R.id.news_layout);
        settingLayout = findViewById(R.id.setting_layout);
        message_image = findViewById(R.id.image_message);
        news_image = findViewById(R.id.image_news);
        setting_image = findViewById(R.id.image_setting);
        message_text = findViewById(R.id.message_text);
        news_text = findViewById(R.id.news_text);
        setting_text = findViewById(R.id.setting_text);
        messageLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:
                setTabSelection(0);
                break;
            case R.id.news_layout:
                setTabSelection(1);
                break;
            case R.id.setting_layout:
                setTabSelection(2);
                break;
            default:
                break;

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        mFragmentTag_INDEX = index;
        switch (index) {
            case 0:
                message_image.setImageResource(R.mipmap.ic_launcher);
                message_text.setTextColor(Color.parseColor("#82858b"));
                if (messageFragment == null) {
                    messageFragment = new Fragment1();
                    transaction.add(R.id.content, messageFragment, FRAGMENT_TAG[index]);
                } else
                    transaction.show(messageFragment);
                break;
            case 1:
                news_image.setImageResource(R.mipmap.ic_launcher);
                news_text.setTextColor(Color.parseColor("#82858b"));
                if (newsFragment == null) {
                    newsFragment = new Fragment2();
                    transaction.add(R.id.content, newsFragment, FRAGMENT_TAG[index]);
                } else
                    transaction.show(newsFragment);
                break;
            case 2:
                setting_image.setImageResource(R.mipmap.ic_launcher);
                setting_text.setTextColor(Color.parseColor("#82858b"));
                if (settingFragment == null) {
                    settingFragment = new Fragment3();
                    transaction.add(R.id.content, settingFragment, FRAGMENT_TAG[index]);
                } else
                    transaction.show(settingFragment);
                break;
        }
        transaction.commit();
    }

    private void clearSelection() {
        message_image.setImageResource(R.drawable.ic_launcher_background);
        message_text.setTextColor(Color.parseColor("#82858b"));
        news_image.setImageResource(R.drawable.ic_launcher_background);
        news_text.setTextColor(Color.parseColor("#82858b"));
        setting_image.setImageResource(R.drawable.ic_launcher_background);
        setting_text.setTextColor(Color.parseColor("#82858b"));

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (messageFragment != null)
            transaction.hide(messageFragment);
        if (newsFragment != null)
            transaction.hide(newsFragment);
        if (settingFragment != null)
            transaction.hide(settingFragment);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putBoolean(FIRST_APP_TAG, true);
        super.onSaveInstanceState(outState, outPersistentState);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initLoading(Bundle savedInstanceState) {
        final RelativeLayout loadingLayout = findViewById(R.id.loadingLayout);
        if (savedInstanceState != null && savedInstanceState.getBoolean(FIRST_APP_TAG)) {
            loadingLayout.setVisibility(View.GONE);
            StatusBarUtils.setColor(this, getResources().getColor(R.color.colorYellow));
            return;
        } else
            loadingLayout.setVisibility(View.VISIBLE);
        setWithoutBar(MainActivity.this);
        getSupportActionBar();

        final TextView timer_text = findViewById(R.id.loading_time_txt);
        ImageView load_img = findViewById(R.id.loading_img_id);
        load_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Glide.with(MainActivity.this).load(mtimgurl).placeholder(R.mipmap.ic_launcher_round).error(R.drawable.ic_launcher_background).into(load_img);
        timer_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.setVisibility(View.GONE);
                StatusBarUtils.setColor(MainActivity.this, getResources().getColor(R.color.colorYellow));
            }
        });
        final Timer timer = new Timer();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    timer_text.setText(recLen + "跳过");
                    if (recLen < 0) {
                        timer.cancel();
                        loadingLayout.setVisibility(View.GONE);
                        StatusBarUtils.setColor(MainActivity.this, getResources().getColor(R.color.colorYellow));
                    }
                }
            }
        };
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                recLen--;
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, 1000, 1000);


    }
}
