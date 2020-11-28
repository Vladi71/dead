package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeId(id: Long)
    fun viewId(id: Long)
    fun removeById(id: Long)
    fun savePost(post: Post)
    fun editById(id: Long, content: String)
}