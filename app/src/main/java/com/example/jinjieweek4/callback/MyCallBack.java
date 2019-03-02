package com.example.jinjieweek4.callback;

public interface MyCallBack<T> {
    void success(T success);
    void error(String error);
}
