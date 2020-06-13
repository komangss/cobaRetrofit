package com.dicoding.cobaretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    //    this will return a Call object, and a list of post
    @GET("posts")
    // because the link is https://jsonplaceholder.typicode.com/posts // its have /posts
    Call<List<Post>> getPost();
//    in java interface we don't declare method body // {}
//    we just declare methods here
//    and this is how retrofit ll take care off
//    to tell retrofit what to do,
//    we have to annotate this method (GET)
}
