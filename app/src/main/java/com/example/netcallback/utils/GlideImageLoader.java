package com.example.netcallback.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.netcallback.R;
import com.youth.banner.loader.ImageLoader;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.black_background)//占位图
                .error(R.mipmap.ic_launcher)//错位图
                .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, 150)
                .bitmapTransform(new RoundedCornersTransformation( context,80,0 ,RoundedCornersTransformation.CornerType.ALL) )
                .into(imageView);

    }
}

