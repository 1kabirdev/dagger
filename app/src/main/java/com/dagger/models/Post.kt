package com.dagger.models

import com.google.gson.Gson

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

data class User(
    val id: Int,
    val name: String,
    val username: String
)

data class Album(
    val id: Int,
    val userId: Int,
    val title: String
)

data class DetailsViewModel(
    val posts: List<Post>,
    val users: List<User>,
    val albums: List<Album>
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}