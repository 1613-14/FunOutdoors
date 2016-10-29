package com.phone.funoutdoors.interfaces;

import com.phone.funoutdoors.bean.ConsultHeaderDetailsLVData;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by White丶 on 2016/10/26.
 */
public interface GetConsultHeaderData {
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST(value = "webservice/qhw1001/func_sub1205")
    Call<ConsultHeaderDetailsLVData> getConsultHeaderData(@Query("pageSize") int pageSize, @Query("featureId") int featureId, @Query("pageNo") int pageNo);
}
