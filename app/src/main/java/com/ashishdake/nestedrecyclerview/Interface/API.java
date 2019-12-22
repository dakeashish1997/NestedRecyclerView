package com.ashishdake.nestedrecyclerview.Interface;

import com.ashishdake.nestedrecyclerview.Models.MainModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    String BASE_URL = "http://192.168.43.254/";

    @FormUrlEncoded
    @POST("datalist.php")
    Call<List<MainModel>> datalist(@FieldMap Map<String, String> data);
}
