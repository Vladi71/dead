package ru.netology.nmedia

import android.app.SearchManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_post.*
import ru.netology.nmedia.adapter.OnItemClickListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.utils.Utils
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnItemClickListener {
            override fun onEdit(post: Post) {
                viewModel.editPost(post)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onLike(post: Post) {
                viewModel.likeId(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareId(post.id)
            }

            override fun onCancelEdit(post: Post) {
                viewModel.cancelEdit()
            }
        })
        binding.listPost.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            binding.editTextAuthor.text = post.content
            group.visibility = View.VISIBLE

            with(binding.contentView) {
                requestFocus()
                setText(post.content)
            }
        }
        binding.saveView.setOnClickListener {
            with(binding.contentView) {
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(this@MainActivity,
                            "Вы не ввели текст!", Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener

                }
                viewModel.changeContent(text.toString())
                viewModel.savePost()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
            group.visibility = View.GONE
        }
        binding.cancelEdit.setOnClickListener {

            with(binding.contentView) {
                viewModel.cancelEdit()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)

            }
            group.visibility = View.GONE
        }
    }
}


