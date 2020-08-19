package ru.vologda.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {
    // аннотация определяет метод, гет или пост, и путь после названия сайта
    // здесь автоматом подставит типа такого localhost:8080/login?user=user&password=password
    @GET("login")
    Call <String> checkUser(
            @Query("user") String user,
            @Query("password") String password
    );
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") int userId
    );
   @POST("posts")
   Call<Post> createPost(@Body Post post);
   @FormUrlEncoded
    @POST("posts")
    Call <Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
   );
}
//   http://jsonplaceholder.typicode.com/posts