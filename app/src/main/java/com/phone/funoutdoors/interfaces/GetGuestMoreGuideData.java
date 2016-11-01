package com.phone.funoutdoors.interfaces;

import com.phone.funoutdoors.bean.Guide;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/31.
 */
public interface GetGuestMoreGuideData {
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST(value = "webservice/qhw2001/func_sub2201")
   Call<Guide> getMoreGuideDate(@Query("pageSize") int pageSize, @Query("pageNo") int pageNo, @Query("userId") int userId );
}
