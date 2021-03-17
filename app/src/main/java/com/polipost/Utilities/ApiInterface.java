package com.polipost.Utilities;

import com.polipost.models.FilterModel;
import com.polipost.models.Login;
import com.polipost.models.ResponseClass;
import com.polipost.models.TemplatesModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("signup")
    Call<ResponseClass> getRegister(
            @Field("email_id") String email_id,
            @Field("password") String password,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("login")
    Call<Login> getLogin(
            @Field("email_id") String email_id,
            @Field("password") String password
    );

//    @FormUrlEncoded
    @POST("category")
    Call<FilterModel> getFilters();

    @POST("tempelatelist")
    Call<TemplatesModel> getTemplats();
}
