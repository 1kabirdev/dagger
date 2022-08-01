package com.dagger.api

import com.dagger.models.Album
import com.dagger.models.Post
import com.dagger.models.User
import com.dagger.utils.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    @GET("albums")
    fun getAlbumList(): Observable<List<Album>>

    @GET("albums/{id}")
    fun getAlbum(@Path("id") id: Int): Observable<Album>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post>

    @GET("users")
    fun getUserList(): Observable<List<User>>

    @GET("posts/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>


    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}