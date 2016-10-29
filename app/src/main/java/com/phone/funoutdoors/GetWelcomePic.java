package com.phone.funoutdoors;

import com.phone.funoutdoors.bean.WelcomePic;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by White丶 on 2016/10/24.
 */
public interface GetWelcomePic {
    @GET(value = "appjson/ad.json")
    Call<WelcomePic> getWelcomePic();
}
