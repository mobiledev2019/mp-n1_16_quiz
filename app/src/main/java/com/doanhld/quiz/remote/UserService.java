package com.doanhld.quiz.remote;

import com.doanhld.quiz.model.Account;
import com.doanhld.quiz.model.ResObj;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/account")
    Call<Account> createAccount(@Body Account account);

    @FormUrlEncoded
    @POST("/api/login")
    Call<ResObj> login(@Field("username") String username, @Field("password") String password);

}
