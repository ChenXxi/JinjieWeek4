package com.example.jinjieweek4.app;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置自定义缓存地址
        DiskCacheConfig images = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("images")//设置文件夹名
                .setBaseDirectoryPath(Environment.getDataDirectory())//设置缓存路径(sd)
                .build();

        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(images)
                .build();

        Fresco.initialize(this,build);
    }
}
