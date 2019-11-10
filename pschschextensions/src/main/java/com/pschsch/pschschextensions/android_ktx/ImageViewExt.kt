package com.pschsch.pschschextensions.android_ktx

import android.os.Build
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.widget.TintableImageSourceView

fun ImageView.setImageTintListCompat(@ColorRes resId : Int) {
    val tintList = context.getColorStateListCompat(resId)
    if (Build.VERSION.SDK_INT >= 21) {
        imageTintList = tintList
        if (Build.VERSION.SDK_INT == 21) {
            val imageViewDrawable = drawable
            if (imageViewDrawable != null && imageTintList != null) {
                if (imageViewDrawable.isStateful) {
                    imageViewDrawable.state = drawableState
                }
                setImageDrawable(imageViewDrawable)
            }
        }
    } else if (this is TintableImageSourceView) {
        (this as TintableImageSourceView).supportImageTintList = tintList
    }
}