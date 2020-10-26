package ru.netology.nmedia.utils

import kotlin.math.floor

class Utils {
    companion object {
        fun formatTotal(count: Int): String {
            return when (count) {
                in 0..999 -> count.toString()
                in 1000..1099 -> String.format("%d", count / 1000) + "K"
                in 1100..9999 -> String.format("%.1f", floor(count / 100.toDouble()) / 10) + "K"
                in 10000..999_999 -> String.format("%d", count / 1000) + "K"
                else -> String.format("%d", count / 1000000) + "M"
            }
        }
    }
}