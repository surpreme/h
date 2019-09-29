package com.example.netcallback.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netcallback.R;
import com.example.netcallback.adapter.SimpleAdapter;
import com.example.netcallback.struct.FunctionManager;

import java.util.Arrays;

public class Fragment2 extends BaseFragment {
    private RecyclerView recyclerView;
    private String[] names = {
            "2017年11月2日  星期四",
            "2017年10月20日  星期五",
            "2017年9月11日  星期一",
            "2017年9月1日  星期五",
            "2017年6月20日  星期二",
            "2018年3月15日  星期日",
            "2017年6月18日  星期一",
    };
    private int[] imgResId = {
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg,
            R.drawable.timg


    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recy);
        recyclerView.setAdapter ( new SimpleAdapter(getContext(), Arrays.asList ( names ) ,imgResId) );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager ( getContext() );
        linearLayoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager ( linearLayoutManager );
        PagerSnapHelper snapHelper = new PagerSnapHelper ();
        snapHelper.attachToRecyclerView ( recyclerView );
    }
}
