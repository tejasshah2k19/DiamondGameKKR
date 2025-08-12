package com.royal.apiservice;

import com.royal.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

   @POST("/api/auth/signup")
   Call<UserModel> signupApi(@Body UserModel userModel);//201

    //@GET("/api/users/leaderboard")
//    Call<List<Object>> leaderboardApi();

}
