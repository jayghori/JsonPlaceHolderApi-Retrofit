
package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofittest.adapter.PostAdapter;
import com.example.retrofittest.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String TAG = "TAGER";

    List<Post> postList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        postList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderService jsonPlaceHolderService = retrofit.create(JsonPlaceHolderService.class);

//        Post post = new Post(12, 0, "tttttttttttt", "bbbbbbbbbbbbbbbbb");
//        jsonPlaceHolderService.addPost(post).enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                if (response.isSuccessful()) {
//                    Post post = response.body();
//                    postList.add(post);
//
//                    PostAdapter postAdapter = new PostAdapter(MainActivity.this, postList);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    recyclerView.setAdapter(postAdapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });


        Call<Post> call=jsonPlaceHolderService.getPostById("2");

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){

                    Post post=response.body();
                    postList.add(post);

                    PostAdapter postAdapter=new PostAdapter(MainActivity.this,postList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(postAdapter);

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }

        });

//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
////                Log.d(TAG, "responseCode: "+response.code());
////                Log.d(TAG, "responseMessage: "+response.message());
////                Log.d(TAG, "responseHeader: "+response.headers());
////                try {
////                    Log.d(TAG, "responseBody: "+response.body().string());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//
//                if(response.isSuccessful()){
//                    postList=response.body();
//
//                    PostAdapter postAdapter=new PostAdapter(MainActivity.this,postList);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    recyclerView.setAdapter(postAdapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//            }
//        });


    }
}