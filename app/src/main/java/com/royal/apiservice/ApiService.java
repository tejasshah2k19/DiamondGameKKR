package com.royal.apiservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {

   @POST("/api/auth/signup")
   Call<Integer> signupApi();//201

    //@GET("/api/users/leaderboard")
//    Call<List<Object>> leaderboardApi();

}
