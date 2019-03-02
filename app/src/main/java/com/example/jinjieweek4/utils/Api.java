package com.example.jinjieweek4.utils;

public class Api {
    public static final String BASE_URL = "http://mobile.bwstudent.com/small/";
    //    首页商品列表
    public static final String HomePageCommodityInformationList = "commodity/v1/commodityList";
    //    商品详情
    public static final String Commoditydetails = "commodity/v1/findCommodityDetailsById";
    //    商品评论
    public static final String Commodityevaluation = "commodity/v1/CommodityCommentList";
    //    同步购物车数据
    public static final String AddToCart = "order/verify/v1/syncShoppingCart";
    //    查询购物车
    public static final String LookCart = "order/verify/v1/findShoppingCart";
}
