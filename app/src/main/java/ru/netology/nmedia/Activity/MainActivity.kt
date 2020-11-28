package ru.netology.nmedia.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_edit_post.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.Activity.EditPostActivity.Companion.id
import ru.netology.nmedia.Activity.EditPostActivity.Companion.text
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnItemClickListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    private val newPostRequestCode = 1
    private val editPostRequestCode = 2

    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(object : OnItemClickListener {
            override fun onEdit(post: Post) {
                val intent = Intent(
                        this@MainActivity, EditPostActivity::class.java).apply {
                    text = post.content
                    id = post.id
                }
                startActivityForResult(intent, editPostRequestCode)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }


            override fun onLike(post: Post) {
                viewModel.likeId(post.id)
            }

            override fun onView(post: Post) {
                viewModel.viewId(post.id)
            }

            override fun onCancelEdit(post: Post) {
                viewModel.cancelEdit()
            }

            override fun onShare(post: Post) {
                Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, post.content)
                        .setType("text/plane")
                        .also {
                            if (it.resolveActivity(packageManager) == null) {
                                Toast.makeText(
                                        this@MainActivity,
                                        "app not found",
                                        Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Intent.createChooser(it, "Поделиться")
                                        .also { startActivity(it) }
                            }
                        }
            }
        })
        binding.listPost.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        binding.addPostView.setOnClickListener {
            startActivityForResult(
                    Intent(this, NewPostActivity::class.java), newPostRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            newPostRequestCode -> {
                data?.extras.let {
                    val postContent = it?.get("contentText").toString()
                    val postContentVideo = it?.get("contentVideo").toString()
                    viewModel.createPost(postContent, postContentVideo)

                }
            }
            editPostRequestCode -> {
                data?.text?.let { text ->
                    data.id?.let { id ->
                        viewModel.editPost(id, text)
                    }
                }
            }
        }
    }
}


