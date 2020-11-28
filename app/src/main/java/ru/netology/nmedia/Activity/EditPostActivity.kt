package ru.netology.nmedia.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.utils.IntentArgs


class EditPostActivity : AppCompatActivity() {

    companion object {

        var Intent.text: String? by IntentArgs.string()
        var Intent.id: Long? by IntentArgs.long()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contentView.requestFocus()
        binding.btmSave.setOnClickListener {
            val intent = Intent()
            if (binding.contentView.text.isNullOrBlank()) {

                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = binding.contentView.text.toString()
                intent.text = content
                intent.id = this.intent.id
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }

        intent.text?.let {
            binding.contentView.setText(it)
            intent.text = null
        }
    }
}
