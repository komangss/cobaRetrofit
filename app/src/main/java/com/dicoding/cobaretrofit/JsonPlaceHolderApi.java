package com.dicoding.cobaretrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    //    this will return a Call object, and a list of post
//    @GET("posts")
    // because the link is https://jsonplaceholder.typicode.com/posts // its have /posts
//    Call<List<Post>> getPost();
//    in java interface we don't declare method body // {}
//    we just declare methods here
//    and this is how retrofit ll take care off
//    to tell retrofit what to do,
//    we have to annotate this method (GET)

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment(@Path("id") int postId); // to tell retrofit the path is use this variable, we use PATH annotation

    //    new getPost // https://jsonplaceholder.typicode.com/posts?userId=userId&_sort=sort&_order=order
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, // if you want use varargs (Integer... userId)  you must place it at last parameter
                              @Query("_sort") String sort,
                              @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getPostsUsingQueryMap(@QueryMap Map<String, String> parameters);

    @GET
    Call<List<Comment>> getCommentsWIthUrl(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostWithFormUrlEncoded(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostWithFieldMap(@FieldMap Map<String, String> fields);


}
