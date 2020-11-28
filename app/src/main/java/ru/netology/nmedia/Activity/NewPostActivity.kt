package ru.netology.nmedia.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_new_post.*
import kotlinx.android.synthetic.main.activity_new_post.view.*
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contentView.requestFocus()
        binding.urlVideo.requestFocus()
        binding.btmSave.setOnClickListener {
            val intent = Intent()
            if (binding.contentView.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val contentText = binding.contentView.text.toString()
                val contentVideo = binding.urlVideo.text.toString()
                intent.putExtra("contentText", contentText)
                intent.putExtra("contentVideo", contentVideo)
                setResult(Activity.RESULT_OK, intent)

            }
            finish()

        }
        binding.addVideoView.setOnClickListener {
            urlVideo.visibility = View.VISIBLE
        }

    }
}