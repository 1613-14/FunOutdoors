package com.phone.funoutdoors.interfaces;

import com.phone.funoutdoors.bean.ConsultLVData;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by White丶 on 2016/10/25.
 */
public interface GetConsultLVData {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST(value = "webservice/qhw1001/func_sub1201")
    Call<ConsultLVData> getConsultRVData(@Query("pageSize") int pageSize, @Query("pageNo") int page);
}
