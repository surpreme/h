package com.example.netcallback.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.netcallback.R;
import com.example.netcallback.bean.ConfigDomain;

import java.util.List;

public class SettingAdapter extends BaseQuickAdapter<ConfigDomain, BaseViewHolder> {
    public SettingAdapter( @Nullable List<ConfigDomain> data) {
        super(R.layout.item_layout,data);


    }


    @Override
    protected void convert(BaseViewHolder helper, ConfigDomain item) {
        helper.setText(R.id.recy_txt_id,item.getText());

    }
}
