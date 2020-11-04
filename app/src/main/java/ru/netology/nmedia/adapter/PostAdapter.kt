package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_post.*
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils
import ru.netology.nmedia.viewmodel.PostViewModel


typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit


class PostAdapter(private val onLikeListener: OnLikeListener,
                  private val onShareListener: OnShareListener) : RecyclerView.Adapter<PostViewHolder>() {
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size

}

class PostViewHolder(
        private val binding: CardPostBinding,
        private val onLikeListener: OnLikeListener,
        private val onShareListener: OnShareListener) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            imgAvatar.setImageResource(post.avatar)
            textAuthor.text = post.author
            textPublished.text = post.published
            textContent.text = post.content
            totalLikeView.text = Utils.formatTotal(post.numberLike)
            totalViewsView.text = Utils.formatTotal(post.numberViews)
            likeAvatarViews.setImageResource(
                    if (post.likeByMy) R.drawable.red_like_avatars else R.drawable.like_avatars
            )
            binding.likeAvatarViews.setOnClickListener {
                onLikeListener(post)
            }
            binding.viewsAvatarView.setOnClickListener {
                onShareListener(post)
            }
        }
    }
}





