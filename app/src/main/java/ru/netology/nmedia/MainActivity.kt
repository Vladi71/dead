package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                textAuthor.text = post.author
                textPublished.text = post.published
                textContent.text = post.content
                totalLikeView.text = Utils.formatTotal(post.numberLike)
                totalViewsView.text = Utils.formatTotal(post.numberViews)
                likeAvatarViews.setImageResource(
                    if (post.likeByMy) R.drawable.red_like_avatars else R.drawable.like_avatars
                )
            }
        }
        binding.likeAvatarViews.setOnClickListener {
            viewModel.like()
        }
        binding.viewsAvatarView.setOnClickListener {
            viewModel.view()
        }
    }
}

