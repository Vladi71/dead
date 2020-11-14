package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post(
        id = 0,
        content = "",
        author = "",
        likeByMy = false,
        published = "",
        numberShare = 0,
        numberViews = 0,
        numberLike = 0

)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun savePost() {
        edited.value?.let {
            repository.savePost(it)
        }
        edited.value = empty
    }

    fun editPost(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeId(id: Long) = repository.likeId(id)
    fun shareId(id: Long) = repository.shareId(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun cancelEdit() {
        edited.value = edited.value
    }
}