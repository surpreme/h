package com.example.netcallback.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.netcallback.R;
import com.example.netcallback.struct.FunctionException;
import com.example.netcallback.struct.FunctionManager;

public class Fragment3 extends BaseFragment {
    public static final String INSTEFACE_RESULTANDPARAMS=Fragment3.class.getName()+"WrWp";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment3, container, false);
       Button button=view.findViewById(R.id.fragmentButton);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mFunctionManager==null)
                   mFunctionManager= FunctionManager.getInstance();
               try {
                   String string=mFunctionManager.invokeFunction(INSTEFACE_RESULTANDPARAMS,123,String.class);
                   Toast.makeText(getContext(),""+string,Toast.LENGTH_LONG).show();
               } catch (FunctionException e) {
                   e.printStackTrace();
               }

           }
       });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }


}
