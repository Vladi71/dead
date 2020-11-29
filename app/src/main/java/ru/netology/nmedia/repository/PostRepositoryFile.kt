package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import java.security.AccessControlContext
import com.google.gson.Gson

class PostRepositoryFile(private val context: Context) : PostRepository {
    private companion object{
        const val FILE = "posts"
        const val POSTS_KEY = "POSTS_KEY"

    }
    private var nextId = 1L
    private val gson = Gson()
    private val preferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)

    private var posts = run {
      val serialized = preferences.getString(POSTS_KEY, null) ?: return@run emptyList<Post>()
    }

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun savePost(post: Post) {
        if (post.id == 0L) {

            posts = listOf(
                    post.copy(
                            id = nextId++,
                            author = "Нетология. Университет интернет-профессий будущего",
                            published = "09 ноября",
                            likeByMy = false,
                            numberLike = 0,
                            numberViews = 0,
                            numberShare = 0
                    )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content, video = post.video)
        }

        data.value = posts
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
    }


    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun editById(id: Long, content: String) {
        val index = posts.indexOfFirst { it.id == id }.takeIf { it >= 0 } ?: return
        posts = posts.toMutableList().apply {
            set(index, posts[index].copy(content = content))
        }
        data.value = posts
    }

}

