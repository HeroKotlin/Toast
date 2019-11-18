package com.github.herokotlin.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_toast.view.*

enum class ToastType {

    LOADING,

    SUCCESS,

    ERROR,

    TEXT

}

enum class ToastDuration {

    SHORT,

    LONG,

}

enum class ToastPosition {

    TOP,

    CENTER,

    BOTTOM

}

object Toast {

    fun show(context: Context, type: ToastType, text: String, duration: ToastDuration, position: ToastPosition) {

        val resources = context.resources

        val yOffset = resources.getDimension(R.dimen.custom_toast_y_offset).toInt()

        val loadingMinWidth = resources.getDimension(R.dimen.custom_toast_loading_min_width).toInt()
        val loadingPaddingHorizontal = resources.getDimension(R.dimen.custom_toast_loading_padding_horizontal).toInt()
        val loadingIconTextSpacing = resources.getDimension(R.dimen.custom_toast_loading_icon_text_spacing).toInt()
        val loadingTextMarginBottom = resources.getDimension(R.dimen.custom_toast_loading_text_margin_bottom).toInt()

        val imageMinWidth = resources.getDimension(R.dimen.custom_toast_image_min_width).toInt()
        val imagePaddingHorizontal = resources.getDimension(R.dimen.custom_toast_image_padding_horizontal).toInt()
        val imageIconTextSpacing = resources.getDimension(R.dimen.custom_toast_image_icon_text_spacing).toInt()
        val imageTextMarginBottom = resources.getDimension(R.dimen.custom_toast_image_text_margin_bottom).toInt()

        val textPaddingHorizontal = resources.getDimension(R.dimen.custom_toast_text_padding_horizontal).toInt()
        val textPaddingVertical = resources.getDimension(R.dimen.custom_toast_text_padding_vertical).toInt()

        val view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)

        view.textView.text = text

        when (type) {
            ToastType.LOADING -> {
                view.minimumWidth = loadingMinWidth
                view.imageView.visibility = View.GONE
                view.loadingView.visibility = View.VISIBLE
                view.textView.setPadding(loadingPaddingHorizontal, loadingIconTextSpacing, loadingPaddingHorizontal, loadingTextMarginBottom)
            }
            ToastType.SUCCESS -> {
                view.minimumWidth = imageMinWidth
                view.imageView.visibility = View.VISIBLE
                view.loadingView.visibility = View.GONE
                view.imageView.setImageResource(R.drawable.custom_toast_success)
                view.textView.setPadding(imagePaddingHorizontal, imageIconTextSpacing, imagePaddingHorizontal, imageTextMarginBottom)
            }
            ToastType.ERROR -> {
                view.minimumWidth = imageMinWidth
                view.imageView.visibility = View.VISIBLE
                view.loadingView.visibility = View.GONE
                view.imageView.setImageResource(R.drawable.custom_toast_error)
                view.textView.setPadding(imagePaddingHorizontal, imageIconTextSpacing, imagePaddingHorizontal, imageTextMarginBottom)
            }
            ToastType.TEXT -> {
                view.imageView.visibility = View.GONE
                view.loadingView.visibility = View.GONE
                view.textView.setPadding(textPaddingHorizontal, textPaddingVertical, textPaddingHorizontal, textPaddingVertical)
            }
        }

        val toast = Toast(context)
        toast.view = view

        toast.duration = when (duration) {
            ToastDuration.LONG -> {
                Toast.LENGTH_LONG
            }
            else -> {
                Toast.LENGTH_SHORT
            }
        }

        when (position) {
            ToastPosition.TOP -> {
                toast.setGravity(Gravity.TOP, 0, yOffset)
            }
            ToastPosition.BOTTOM -> {
                toast.setGravity(Gravity.BOTTOM, 0, yOffset)
            }
            else -> {
                toast.setGravity(Gravity.CENTER, 0, 0)
            }
        }

        toast.show()

    }
}