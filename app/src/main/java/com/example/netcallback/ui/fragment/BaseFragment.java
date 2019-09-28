package com.example.netcallback.ui.fragment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.netcallback.struct.FunctionManager;

public class BaseFragment extends Fragment{
    protected FunctionManager mFunctionManager;

    public void setmFunctionManager(FunctionManager mFunctionManager) {
        this.mFunctionManager = mFunctionManager;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
