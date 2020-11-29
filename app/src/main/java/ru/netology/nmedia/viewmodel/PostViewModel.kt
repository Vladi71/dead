package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryFile

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

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFile(application)
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