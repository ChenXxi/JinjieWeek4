package com.example.jinjieweek4.model;

import com.example.jinjieweek4.callback.MyCallBack;
import com.example.jinjieweek4.utils.OkUtil;
import com.google.gson.Gson;

import java.util.Map;

public class ModelImpl implements Model {

    @Override
    public void post(String murl, Map<String, Object> headeremap, Map<String, Object> map, final Class aClass, final MyCallBack myCallBack) {
        OkUtil.getInstance().post(murl,headeremap,map).setHttpListener(new OkUtil.HttpListener() {
            @Override
            public void onSuccess(String gsonstr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(gsonstr,aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void get(String murl, Map<String, Object> headermap, Map<String, Object> map, final Class aClass, final MyCallBack myCallBack) {
        OkUtil.getInstance().get(murl,headermap,map).setHttpListener(new OkUtil.HttpListener() {
            @Override
            public void onSuccess(String gsonstr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(gsonstr,aClass);
                myCallBack.success(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.error(error);
            }
        });
    }
}
