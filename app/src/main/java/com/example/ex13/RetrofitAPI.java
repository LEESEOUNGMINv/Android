package com.example.ex13;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//    @GET("주소")
//    Call<받아오는 형태 = 스프링에서 return 하는 값> 메서드 명();
public interface RetrofitAPI {
    @POST("/logistics_erp/loginAction")
    Call<String> login(@Query("map") Map<String, String> map);

}