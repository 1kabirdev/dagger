package com.dagger.models

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