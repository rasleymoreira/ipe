package com.example.institutodepesquisaseldorado.util

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.institutodepesquisaseldorado.util.Constants.BASE_IMG_URL

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun String.limitDescription(characters: Int): String {
    if (this.length > characters) {
        val firstCharacter = 0
        return "${this.substring(firstCharacter, characters)}..."
    }
    return this
}

fun loadImage(imageView: ImageView, path: String) {
    Glide.with(imageView.context)
        .load(BASE_IMG_URL + path)
        .into(imageView)
}
