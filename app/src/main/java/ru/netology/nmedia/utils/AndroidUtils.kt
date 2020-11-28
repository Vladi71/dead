package ru.netology.nmedia.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object AndroidUtils {
    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
object IntentArgs {


    fun string(): ReadWriteProperty<Intent, String?> = StringDelegate
    fun long(): ReadWriteProperty<Intent, Long?> = LongDelegate

    private object StringDelegate: ReadWriteProperty<Intent, String?> {

        override fun setValue(thisRef: Intent, property: KProperty<*>, value: String?) {
            thisRef.putExtra(property.name, value)
        }

        override fun getValue(thisRef: Intent, property: KProperty<*>): String? =
                thisRef.getStringExtra(property.name)
    }

    private object LongDelegate: ReadWriteProperty<Intent, Long?> {
        override fun setValue(thisRef: Intent, property: KProperty<*>, value: Long?) {
            thisRef.putExtra(property.name, value)
        }

        override fun getValue(thisRef: Intent, property: KProperty<*>): Long? =
                thisRef.getSerializableExtra(property.name) as? Long
    }
}