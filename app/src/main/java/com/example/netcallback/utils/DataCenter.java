package com.example.netcallback.utils;

import com.example.netcallback.App;
import com.example.netcallback.BuildConfig;
import com.example.netcallback.R;
import com.example.netcallback.bean.ConfigDomain;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {
    public static List<ConfigDomain> getConfigDomainLists() {
        List<ConfigDomain> list = new ArrayList<>();
        list.add(new ConfigDomain(App.getContext().getString(R.string.str_add_accounts)));
        list.add(new ConfigDomain(App.getContext().getString(R.string.str_config_always_run_app)));
        list.add(new ConfigDomain(App.getContext().getString(R.string.str_config_wifi)));
        list.add(new ConfigDomain(App.getContext().getString(R.string.str_config_data)));
        list.add(new ConfigDomain(App.getContext().getString(R.string.str_system)));
        if (BuildConfig.DEBUG) {
            list.add(new ConfigDomain(App.getContext().getString(R.string.str_test_ntp)));
            list.add(new ConfigDomain(App.getContext().getString(R.string.str_fota_test)));//todo just for test
            list.add(new ConfigDomain(App.getContext().getString(R.string.str_richdemo_slient_uninstall_test)));//todo just for test
            list.add(new ConfigDomain(App.getContext().getString(R.string.str_slient_install_richdemo)));//todo just for test
        }
//        list.add(new ConfigDomain(MyApplication.getContext().getString(R.string.str_toolbar_exit)));
//        list.add(new ConfigDomain(MyApplication.getContext().getString(R.string.str_test_autoinstall)));
        return list;
    }
}
