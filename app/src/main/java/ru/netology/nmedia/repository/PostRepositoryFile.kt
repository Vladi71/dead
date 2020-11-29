package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositoryFile(
    private val context: Context
) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"
    private var nextId = 1L
    private var posts = emptyList<Post>()
    private val mLData = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {

            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                mLData.value = posts
            }
        } else {

            sync()
        }
    }

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun savePost(post: Post) {
        if (post.id == 0L) {

            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    published = "29 ноября",
                    likeByMy = false,
                    numberLike = 0,
                    numberViews = 0,
                    numberShare = 0
                )
            ) + posts
            data.value = posts
            sync()
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content, video = post.video)
        }

        data.value = posts
        sync()
    }

    override fun likeId(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(
                    likeByMy = !it.likeByMy, numberLike = if (it.likeByMy) it.numberLike - 1
                    else it.numberLike + 1
                )
            }
        }
        data.value = posts
        sync()
    }


    override fun viewId(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(numberViews = it.numberViews + 1)
            }
        }
        data.value = posts
        sync()
    }


    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
    }

    override fun editById(id: Long, content: String) {
        val index = posts.indexOfFirst { it.id == id }.takeIf { it >= 0 } ?: return
        posts = posts.toMutableList().apply {
            set(index, posts[index].copy(content = content))
        }
        data.value = posts
        sync()
    }

    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
}

