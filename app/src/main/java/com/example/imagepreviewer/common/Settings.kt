package com.example.imagepreviewer.common

import android.content.Context

object Settings {

    private const val THEME = "chosen_theme"
    private const val KEY_THEME = "key_theme"

    fun saveTheme(context: Context, theme: Boolean) {
        context.getSharedPreferences(THEME, Context.MODE_PRIVATE).edit()
            .putBoolean(KEY_THEME, theme).apply()
    }

    fun loadTheme(context: Context): Boolean {
        return context.getSharedPreferences(THEME, Context.MODE_PRIVATE).getBoolean(KEY_THEME, true)
    }
}