package com.example.jinjieweek4.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OkUtil {
    private MyApiService myApiService;

    public OkUtil(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .build();
        myApiService = retrofit.create(MyApiService.class);
    }

    public static OkUtil getInstance(){
        return RetroHolder.OK_UTIL;
    }


    static class RetroHolder {
        private static final OkUtil OK_UTIL = new OkUtil();
    }

    /**
     * Get请求
     * @param url
     * @param headermap
     * @param map
     * @return
     */
    public OkUtil get(String url, Map<String,Object> headermap,Map<String,Object> map){
        myApiService.get(url, headermap, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return OkUtil.getInstance();
    }

    /**
     * post请求
     * @param url
     * @param headermap
     * @param map
     * @return
     */
    public OkUtil post(String url,Map<String,Object> headermap,Map<String,Object> map){
        if (map ==null){
            map = new HashMap<>();
        }
        myApiService.post(url, headermap, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return OkUtil.getInstance();
    }


    /**
     * 封装一个delete 请求方式
     *
     */
    public OkUtil delete(String murl,Map<String,Object> headermap,Map<String,Object> map){
        myApiService.delete(murl,headermap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return OkUtil.getInstance();
    }

    //    重写一个观察者模式
    private Observer observer =new Observer<ResponseBody>(){

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if(httpListener!=null){
                httpListener.onError(e.getMessage());
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            if(httpListener !=null){
                try {
                    httpListener.onSuccess(responseBody.string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public interface HttpListener{
        void onSuccess(String gsonstr);
        void onError(String error);
    }

    private HttpListener httpListener;

    public void setHttpListener(HttpListener httpListener) {
        this.httpListener = httpListener;
    }
}
