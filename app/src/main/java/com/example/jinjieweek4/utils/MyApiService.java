package com.example.jinjieweek4.utils;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String murl, @HeaderMap Map<String, Object> headermap, @QueryMap Map<String, Object> map);

    @POST
    Observable<ResponseBody> post(@Url String murl, @HeaderMap Map<String, Object> headermap, @QueryMap Map<String, Object> map);

    @PUT
    Observable<ResponseBody> put(@Url String murl, @HeaderMap Map<String, Object> headermap, @QueryMap Map<String, Object> map);

    @DELETE
    Observable<ResponseBody> delete(@Url String murl, @HeaderMap Map<String, Object> headermap, @QueryMap Map<String, Object> map);
}
