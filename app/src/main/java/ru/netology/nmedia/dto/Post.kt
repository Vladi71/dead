package ru.netology.nmedia.dto

import kotlinx.android.synthetic.main.activity_main.view.*
import ru.netology.nmedia.R

data class Post(
        val id: Long,
        val avatar: Int = R.mipmap.ic_launcher,
        val author: String,
        val content: String,
        val published: String,
        val numberLike: Int,
        val numberShare: Int,
        val numberViews: Int,
        val likeByMy: Boolean = false
)