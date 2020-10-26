package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var numberLike: Int,
    var numberShare: Int,
    var numberViews: Int,
    var likeByMy: Boolean = false
)