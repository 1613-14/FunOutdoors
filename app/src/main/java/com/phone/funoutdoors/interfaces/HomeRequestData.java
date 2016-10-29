package com.phone.funoutdoors.interfaces;


import com.phone.funoutdoors.bean.Direction;
import com.phone.funoutdoors.bean.HomeInfo;
import com.phone.funoutdoors.bean.Scene;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 首页信息请求的接口
 */
public interface HomeRequestData {
    @GET("appjson/index_new.json")
    Call<HomeInfo> getContentData();

    @GET("appjson/appconfig.json")
    Call<Direction> getDirectionData();

    @POST("webservice/qhw1001/func_sub1006")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<Scene> getSceneData(@Query("destination_id") int destination_id);


}
