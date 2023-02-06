package com.example.lwmovieapp.network;

import com.example.lwmovieapp.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("5b9d8324-0cc3-473a-b73f-a434b6530ba6")
    Call<List<MovieModel>> getMovieList();
}
