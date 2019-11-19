package com.github.herokotlin.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_toast.view.*

class Toast constructor(private val context: Context) {

    private val yOffset = getDimension(R.dimen.custom_toast_y_offset)

    private val imageMinWidth = getDimension(R.dimen.custom_toast_image_min_width)
    private val imagePaddingHorizontal = getDimension(R.dimen.custom_toast_image_padding_horizontal)
    private val imageIconTextSpacing = getDimension(R.dimen.custom_toast_image_icon_text_spacing)
    private val imageTextMarginBottom = getDimension(R.dimen.custom_toast_image_text_margin_bottom)

    private val textPaddingHorizontal = getDimension(R.dimen.custom_toast_text_padding_horizontal)
    private val textPaddingVertical = getDimension(R.dimen.custom_toast_text_padding_vertical)

    private fun getDimension(resId: Int): Int {
        return context.resources.getDimension(resId).toInt()
    }

    fun show(text: String, type: String, duration: String, position: String) {

        val view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)

        view.textView.text = text

        when (type) {
            "success" -> {
                view.minimumWidth = imageMinWidth
                view.imageView.visibility = View.VISIBLE
                view.imageView.setImageResource(R.drawable.custom_toast_success)
                view.textView.setPadding(imagePaddingHorizontal, imageIconTextSpacing, imagePaddingHorizontal, imageTextMarginBottom)
            }
            "error" -> {
                view.minimumWidth = imageMinWidth
                view.imageView.visibility = View.VISIBLE
                view.imageView.setImageResource(R.drawable.custom_toast_error)
                view.textView.setPadding(imagePaddingHorizontal, imageIconTextSpacing, imagePaddingHorizontal, imageTextMarginBottom)
            }
            else -> {
                view.imageView.visibility = View.GONE
                view.textView.setPadding(textPaddingHorizontal, textPaddingVertical, textPaddingHorizontal, textPaddingVertical)
            }
        }

        val toast = Toast(context)
        toast.view = view

        toast.duration = when (duration) {
            "long" -> {
                Toast.LENGTH_LONG
            }
            else -> {
                Toast.LENGTH_SHORT
            }
        }

        when (position) {
            "top" -> {
                toast.setGravity(Gravity.TOP, 0, yOffset)
            }
            "bottom" -> {
                toast.setGravity(Gravity.BOTTOM, 0, yOffset)
            }
            else -> {
                toast.setGravity(Gravity.CENTER, 0, 0)
            }
        }

        toast.show()

    }
}