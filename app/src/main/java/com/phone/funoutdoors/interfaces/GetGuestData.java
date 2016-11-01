package com.phone.funoutdoors.interfaces;

import com.phone.funoutdoors.bean.GuestData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/31.
 */
public interface GetGuestData {
    @GET(value = "appjson/qukeindex.json")
   Call<GuestData> getDate();
}
