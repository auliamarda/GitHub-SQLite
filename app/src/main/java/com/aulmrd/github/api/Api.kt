package com.aulmrd.github.api

import com.aulmrd.github.data.DetailUserResponse
import com.aulmrd.github.data.User
import com.aulmrd.github.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET(value =  "search/users")
    @Headers(value = arrayOf("Authorization: token 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"))
    fun getSearchUsers(
        @Query(value = "q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers ("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
        ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun  getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}