package com.codezilla.osaid.palstreet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by osaid on 12/24/2017.
 */

public interface WebService {

    String BASE_URL = "http://palstreet.info/public/";

    @GET("get-news")
    Call<List<News>> getNews();




}
