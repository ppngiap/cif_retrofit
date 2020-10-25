package com.example.retrofit1

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {
    @GET("posts")
    fun posts(): Call<List<Post?>?>?

    @GET("posts")
    fun posts(
        @Query("userId") userId: Array<Int?>?,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<Post?>?>?

    @GET("posts")
    fun posts(@QueryMap parameters: Map<String?, String?>?): Call<List<Post?>?>?

    @GET("posts/{id}/comments")
    fun comments(@Path("id") postId: Int): Call<List<Comment?>?>?

    @GET
    fun comments(@Url url: String?): Call<List<Comment?>?>?
}
/*

package com.codinginflow.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);
    @GET
    Call<List<Comment>> getComments(@Url String url);
}
*/
