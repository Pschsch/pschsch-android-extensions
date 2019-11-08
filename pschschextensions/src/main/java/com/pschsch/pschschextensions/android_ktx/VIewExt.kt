package com.pschsch.pschschextensions.android_ktx

import android.os.Build
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.widget.TintableImageSourceView

/**Use it only, if you don't handle View.INVISIBLE state and need to depend visibility on boolean value for cleaner code**/
fun View.setVisible(boolean: Boolean) {
    visibility = if (boolean) VISIBLE else GONE
}

fun View.fakeTouch() {
    val downTime = SystemClock.uptimeMillis()
    val eventTime = SystemClock.uptimeMillis() + 100
    val x = 0.0f
    val y = 0.0f
    val metaState = 0
    val motionEvent = MotionEvent.obtain(
        downTime,
        eventTime,
        MotionEvent.ACTION_UP,
        x,
        y,
        metaState
    )
    dispatchTouchEvent(motionEvent)
    motionEvent.recycle()
}

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
