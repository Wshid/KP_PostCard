package com.example.jinsu.posters.Data;

import com.example.jinsu.posters.Model.Gift;
import com.example.jinsu.posters.Model.MyUser;
import com.example.jinsu.posters.Model.User;
import com.example.jinsu.posters.Model.Test;
import com.example.jinsu.posters.Model.Test2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroService {
    // url : http://api.github.com/users/meansoup
    //baseUrl뒤에 붙음 , Path가 url뒤에 붙음
    @GET("/users/{KEY}")
    Call<Test2> getRespos(@Path("KEY") String id);

    @GET("/All_Gift")
    Call<ArrayList<Gift>> getGift();



    //자신이 가지고 있는 혜택
    @GET("/postCardKey")
    Call<ArrayList<Gift>> getMyGift(@Query("card_key") String card_key);

    @GET("/user")
    Call<User> getUser();

    @GET("/findAll")
    Call<List<Test>> get();

    @POST("/registration")
    Call<Test> post(@Body Test test);

    /*@GET("/users/{KEY}")
    Call<User> getUser(@Path("KEY") String key, @Path("id") String id);*/

    @POST("/users/{KEY}")
    Call<User> postRespos(@Path("KEY") String id, @Body User user);

    @POST("/updateGift")
    Call<String> updateGift(@Body MyUser user);
}
