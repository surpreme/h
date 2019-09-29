package com.example.netcallback.ui.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.netcallback.Constant;
import com.example.netcallback.R;
import com.example.netcallback.adapter.SettingAdapter;
import com.example.netcallback.bean.ConfigDomain;
import com.example.netcallback.struct.FunctionException;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.activity.SettingActvity;
import com.example.netcallback.utils.DataCenter;
import com.example.netcallback.utils.LogUtils;

public class Fragment3 extends BaseFragment {
    private SettingAdapter settingAdapter;
    public static final String INSTERFACE_PARAMS = Fragment1.class.getName() + "WpNr";
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.setting_recy_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(settingAdapter = new SettingAdapter(DataCenter.getConfigDomainLists()));
        settingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ConfigDomain configDomain = (ConfigDomain) adapter.getData().get(position);
                if (configDomain != null) {
                    {
                        String extras = "";
                        if (configDomain.getText().toString().equals(getString(R.string.str_config_always_run_app))) {
                            extras = getString(R.string.str_config_always_run_app);
                        } else if (configDomain.getText().toString().equals(getString(R.string.str_toolbar_exit))) {
                            extras = getString(R.string.str_toolbar_exit);

                        } else if (configDomain.getText().toString().equals(getString(R.string.str_config_wifi))) {
                            extras = getString(R.string.str_config_wifi);

                        } else if (configDomain.getText().toString().equals(getString(R.string.str_config_data))) {
                            extras = getString(R.string.str_config_data);

                        } else if (configDomain.getText().toString().equals(getString(R.string.str_test_autoinstall))) {
                            extras = getString(R.string.str_test_autoinstall);
                        } else if (configDomain.getText().toString().equals(getString(R.string.str_system))) {
                            extras = getString(R.string.str_system);

                        } else if (configDomain.getText().toString().equals(getString(R.string.str_test_ntp))) {
                            extras = getString(R.string.str_test_ntp);
                        } else if (configDomain.getText().toString().equals(getString(R.string.str_fota_test))) {
                            extras = getString(R.string.str_fota_test);
                        } else if (configDomain.getText().toString().equals(getString(R.string.str_richdemo_slient_uninstall_test))) {
                            extras = getString(R.string.str_richdemo_slient_uninstall_test);
                        } else if (configDomain.getText().toString().equals(getString(R.string.str_slient_install_richdemo))) {
                            extras = getString(R.string.str_slient_install_richdemo);
                        } else if (configDomain.getText().toString().equals(getString(R.string.str_add_accounts))) {
                            extras = getString(R.string.str_add_accounts);

                        }
                        Intent intent = new Intent(getContext(), SettingActvity.class);
                        Bundle bud = new Bundle();
                        bud.putString(Constant.SETTING_STRING, extras);
                        LogUtils.d(extras);
                        intent.putExtras(bud);
                        startActivity(intent);

                    }
                }

            }
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }


}
