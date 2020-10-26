package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
                id = 321,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                published = "21 мая в 18:36",
                numberLike = 1099,
                numberShare = 12,
                numberViews = 999_999,
                likeByMy = false
        )


        with(binding) {
            textAuthor.text = post.author
            textPublished.text = post.published
            textContent.text = post.content
            totalLikeView.text = Utils.formatTotal(post.numberLike)
            totalViewsView.text = Utils.formatTotal(post.numberViews)

            if (post.likeByMy) {
                likeAvatarViews.setImageResource(R.drawable.red_like_avatars)
            }
            likeAvatarViews.setOnClickListener {
                post.likeByMy = !post.likeByMy
                if (post.likeByMy){
                    likeAvatarViews.setImageResource(R.drawable.red_like_avatars)
                    post.numberLike++
                }else{
                    likeAvatarViews.setImageResource(R.drawable.like_avatars)
                    post.numberLike--

                }
                totalLikeView.text = Utils.formatTotal(post.numberLike)
            }
            viewsAvatarView.setOnClickListener{
                post.numberViews++
                totalViewsView.text = Utils.formatTotal(post.numberViews)
            }
        }
    }
}
