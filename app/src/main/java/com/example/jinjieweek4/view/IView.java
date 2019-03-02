package com.example.jinjieweek4.view;

public interface IView<T> {
    void success(T success);
    void error(String error);
}
