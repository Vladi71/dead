package ru.netology.nmedia.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.card_post.view.*
import ru.netology.nmedia.databinding.ActivityIntentHandler2Binding


class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandler2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.getStringExtra(Intent.EXTRA_TEXT)?.let {
            binding.root.text = it
        } ?: run {
            Snackbar.make(binding.root, "Text not found", Snackbar.LENGTH_SHORT).show()
        }
    }
}