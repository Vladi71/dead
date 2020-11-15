package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils


interface OnItemClickListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onCancelEdit(post: Post) {}

}

class PostAdapter(
        private val onItemClickListener: OnItemClickListener
) : ListAdapter<Post, PostViewHolder>(PostViewHolder.PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}


class PostViewHolder(
        private val binding: CardPostBinding,
        private val onInteractionListener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            imgAvatar.setImageResource(post.avatar)
            textAuthor.text = post.author
            textPublished.text = post.published
            textContent.text = post.content
            likeAvatarViews.isChecked = post.likeByMy
            viewsAvatarView.text = Utils.formatTotal(post.numberViews)
            shareAvatarsView.text = Utils.formatTotal(post.numberShare)
            likeAvatarViews.text = Utils.formatTotal(post.numberLike)

            menuAvatar.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.removePostView -> {
                                onInteractionListener.onRemove(post)

                                true
                            }
                            R.id.editPostView -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
            binding.likeAvatarViews.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            binding.viewsAvatarView.setOnClickListener {
                onInteractionListener.onShare(post)
            }
        }
    }


    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

}





