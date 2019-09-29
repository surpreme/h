package com.example.netcallback.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;

import com.example.netcallback.Constant;
import com.example.netcallback.R;
import com.example.netcallback.struct.FuctionNoParamNoResult;
import com.example.netcallback.struct.FuctionWithParamOnly;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.fragment.BaseFragment;
import com.example.netcallback.ui.fragment.Fragment1;
import com.example.netcallback.ui.fragment.Fragment2;
import com.example.netcallback.ui.fragment.Fragment3;
import com.example.netcallback.utils.PopwindowUtil;
import com.example.netcallback.utils.ToastUtils;

public class SettingActvity extends BaseActivity {
    private TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_layout);
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle=this.getIntent().getExtras();
        if (bundle!=null&& !TextUtils.isEmpty(String.valueOf(bundle.getString(Constant.SETTING_STRING))))
            textView.setText(String.valueOf(bundle.getString(Constant.SETTING_STRING)));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        textView=findViewById(R.id.setting_txt_activity_id);
        LinearLayout linearLayout=findViewById(R.id.setting_all_layout);
        linearLayout.setOnTouchListener(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopwindowUtil.getmInstance().showCancelPopupWindow(SettingActvity.this,R.layout.setting_activity_layout);
            }
        });
    }
    public void setFunctionForFragment(String tag) {
        FragmentManager f = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) f.findFragmentByTag(tag);
        FunctionManager functionManager = FunctionManager.getInstance();
        if (fragment == null)
            fragment = new BaseFragment();
        fragment.setmFunctionManager(functionManager
               .addFunction(new FuctionWithParamOnly(Fragment3.INSTERFACE_PARAMS) {

                    @Override
                    public void function(Object o) {
                        if (!TextUtils.isEmpty(String.valueOf(o)))
                        textView.setText(String.valueOf(o));

                    }
                }));
    }
}
