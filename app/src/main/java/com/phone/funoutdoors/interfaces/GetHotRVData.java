package com.phone.funoutdoors.interfaces;

import com.phone.funoutdoors.bean.Hot;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by White丶 on 2016/10/27.
 */
public interface GetHotRVData  {
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST(value = "webservice/qhw3001/func_sub1001")
    Call<Hot> getHotRVData(@Query("pageSize") int pageSize, @Query("orderby") int orderby, @Query("cityId") int cityId, @Query("myId") int myId, @Query("pageNo") int pageNo, @Query("userId") int userId);
}
