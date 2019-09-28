package com.example.netcallback.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.netcallback.R;
import com.example.netcallback.struct.FunctionException;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.activity.MainActivity;
import com.example.netcallback.utils.GlideImageLoader;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Fragment1 extends BaseFragment {
    private Banner banner;
    private ArrayList<String> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        banner = view.findViewById(R.id.banner);
        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new WaterDropHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NotNull RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NotNull RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        arrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        arrayList.add("http://img.mukewang.com/55237dcc0001128c06000338.jpg");
        arrayList.add("http://img.mukewang.com/55249cf30001ae8a06000338.jpg");
        arrayList.add("http://img.mukewang.com/5523711700016d1606000338.jpg");
        arrayList.add("http://img.mukewang.com/551e470500018dd806000338.jpg");
        banner.setImageLoader(new GlideImageLoader());
        //设置动画效果
        banner.setBannerAnimation(Transformer.Tablet);
        //设置样式 带圆指示器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //指示器的位置
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //自动轮播是否开启
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.setImages(arrayList);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), position + "被点击了", Toast.LENGTH_LONG).show();
            }
        });
        banner.start();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
