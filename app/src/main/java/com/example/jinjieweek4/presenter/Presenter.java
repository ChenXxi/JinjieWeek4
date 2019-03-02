package com.example.jinjieweek4.presenter;

import java.util.Map;

public interface Presenter {
    //post
    void post(String murl, Map<String,Object> headermap, Map<String,Object> map, Class aClass);

    //get
    void get(String murl, Map<String,Object> headermap,Map<String,Object> map,Class aClass);
}
