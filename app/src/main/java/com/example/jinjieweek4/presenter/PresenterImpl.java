package com.example.jinjieweek4.presenter;

import com.example.jinjieweek4.callback.MyCallBack;
import com.example.jinjieweek4.model.ModelImpl;
import com.example.jinjieweek4.view.IView;

import java.util.Map;

public class PresenterImpl implements Presenter, MyCallBack {
    private ModelImpl model;
    private IView iView;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }

    @Override
    public void success(Object success) {
        iView.success(success);
    }

    @Override
    public void error(String error) {
        iView.error(error);
    }

    public void onDatch(){
        if (iView != null){
            iView = null;
        }
        if (model != null){
            model = null;
        }
    }

    @Override
    public void post(String murl, Map<String, Object> headermap, Map<String, Object> map, Class aClass) {
        model.post(murl,headermap,map,aClass,this);
    }

    @Override
    public void get(String murl, Map<String, Object> headermap, Map<String, Object> map, Class aClass) {
        model.get(murl,headermap,map,aClass,this);
    }
}
