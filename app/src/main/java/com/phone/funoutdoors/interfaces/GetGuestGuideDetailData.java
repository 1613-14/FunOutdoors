package com.phone.funoutdoors.interfaces;

import com.phone.funoutdoors.bean.GuideDetail;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/31.
 */
public interface GetGuestGuideDetailData {
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST(value = "webservice/qhw2001/func_sub2202")
   Call<GuideDetail> getMoreGuideDate(@Query("myId") int myId, @Query("actId") int actId);
}
