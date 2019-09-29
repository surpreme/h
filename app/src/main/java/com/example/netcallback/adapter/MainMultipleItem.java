package com.example.netcallback.adapter;


import android.graphics.ColorSpace;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MainMultipleItem implements MultiItemEntity {
    static final int FIRST_TYPE = 1;
    static final int SECOND_TYPE = 2;
    static final int NORMAL_TYPE = 3;
    private int itemType;

    public MainMultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}