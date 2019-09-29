package com.example.netcallback.struct;

import android.text.TextUtils;

import java.util.HashMap;

import javax.xml.transform.Result;

public class FunctionManager {
    private volatile static FunctionManager mInstance;

    private FunctionManager() {
        mFunctionNoParamNoResult = new HashMap<>();
        mFuctionWithParamOnly = new HashMap<>();
        mFuctionWithResultOnly = new HashMap<>();
        mFuctionWithParamAndResult = new HashMap<>();

    }

    /**
     * 单例模式
     *
     * @return
     */
    public static FunctionManager getInstance() {
        if (mInstance == null) {
            synchronized (FunctionManager.class) {
                mInstance = new FunctionManager();
            }
        }
        return mInstance;
    }

    private HashMap<String, FuctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String, FuctionWithParamOnly> mFuctionWithParamOnly;
    private HashMap<String, FuctionWithResultOnly> mFuctionWithResultOnly;
    private HashMap<String, FuctionWithParamAndResult> mFuctionWithParamAndResult;

    public FunctionManager addFunction(FuctionNoParamNoResult function) {
        mFunctionNoParamNoResult.put(function.mFunctionName, function);
        return this;
    }

    public FunctionManager addFunction(FuctionWithParamOnly function) {
        mFuctionWithParamOnly.put(function.mFunctionName, function);
        return this;
    }

    public FunctionManager addFunction(FuctionWithResultOnly function) {
        mFuctionWithResultOnly.put(function.mFunctionName, function);
        return this;
    }

    public FunctionManager addFunction(FuctionWithParamAndResult function) {
        mFuctionWithParamAndResult.put(function.mFunctionName, function);
        return this;
    }

    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) return;
        if (mFunctionNoParamNoResult != null) {
            FuctionNoParamNoResult f = mFunctionNoParamNoResult.get(functionName);
            if (f != null) f.funtion();
            if (f == null) {
                try {
                    throw new FunctionException("Has no this function" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <Result> Result invokeFunction(String functionName, Class<Result> claz) {
        if (TextUtils.isEmpty(functionName)) return null;
        if (mFuctionWithResultOnly != null) {
            FuctionWithResultOnly f = mFuctionWithResultOnly.get(functionName);
            if (f != null) {
                if (claz != null) {
                    return claz.cast(f.function());
                } else {
                    return (Result) f.function();
                }
            } else {
                try {
                    throw new FunctionException("Has no this function" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public <Params> void invokeFunction(String functionName, Params data) throws FunctionException {
        if (TextUtils.isEmpty(functionName)) return;
        if (mFuctionWithParamOnly != null) {
            FuctionWithParamOnly f = mFuctionWithParamOnly.get(functionName);
            if (f != null) {
                f.function(data);
            } else {

                throw new FunctionException("Has no this function" + functionName);

            }
        }
    }

    public <Result, Params> Result invokeFunction(String functionName, Params data, Class<Result> claz) throws FunctionException {
        if (TextUtils.isEmpty(functionName)) return null;
        if (mFuctionWithParamAndResult != null) {
            FuctionWithParamAndResult f = mFuctionWithParamAndResult.get(functionName);
            if (f != null) {
                if (claz != null)
                    return claz.cast(f.function(data));
                else
                    return (Result) f.function(data);
            } else {
                    throw new FunctionException("Has no this function" + functionName);


            }
        }
        return null;
    }

}
