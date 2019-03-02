package com.example.jinjieweek4.model;

import com.example.jinjieweek4.callback.MyCallBack;

import java.util.Map;

public interface Model {

    //post
    void post(String murl, Map<String,Object> headeremap, Map<String,Object> map, Class aClass, MyCallBack myCallBack);

    //get
    void get(String murl,Map<String,Object> headermap,Map<String,Object> map,Class aClass,MyCallBack myCallBack);
}
