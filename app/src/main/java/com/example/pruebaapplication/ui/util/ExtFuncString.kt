package com.example.pruebaapplication.ui.util

import android.graphics.Color
import android.graphics.Typeface
import java.util.Locale

fun String.toTextDrawable(
    textColor: Int = Color.WHITE,
    backgroundColor: Int = Color.RED
): TextDrawable = TextDrawable
    .builder()
    .beginConfig()
    .useFont(Typeface.DEFAULT)
    .textColor(textColor)
    .endConfig()
    .build(this, backgroundColor)


fun String.getInitials(): String {
    val regex = Regex("(?i)(?:^|\\s|-)+([^\\s-])[^\\s-]*")
    val matches = regex.findAll(this)
    val initials = matches.map { it.groupValues[1] }.joinToString("").uppercase(Locale.ROOT)
    return initials.take(2)
}