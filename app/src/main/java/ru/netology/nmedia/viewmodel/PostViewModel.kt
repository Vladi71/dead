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
        numberLike = 0,
        video = ""


)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun createPost(content: String, postContentVideo: String) = repository.savePost(
            empty.copy(content = content, video = postContentVideo)
    )


    fun editPost(id: Long, content: String) = repository.editById(id, content)


    fun likeId(id: Long) = repository.likeId(id)
    fun viewId(id: Long) = repository.viewId(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun cancelEdit() {
        edited.value = edited.value
    }
}