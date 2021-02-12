package com.example.retrofittest;

import com.example.retrofittest.model.Post;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderService {

//    @GET("posts")
//    Call<ResponseBody> getAllPosts();

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @POST("posts")
    Call<Post> addPost(@Body Post post);

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") String id);

}
