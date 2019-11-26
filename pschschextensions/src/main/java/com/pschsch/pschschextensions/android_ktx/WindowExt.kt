package com.pschsch.pschschextensions.android_ktx

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.Window
import androidx.annotation.ColorRes

fun Window.setLightStatusBarCompat(@ColorRes resId : Int? = null, context : Context? = null) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        resId?.let {
            if(context != null) {
                statusBarColor = context.getColorCompat(it)
            }
        }
    }
}

fun Window.setDefaultStatusBarCompat(@ColorRes resId : Int? = null, context : Context? = null) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.decorView.systemUiVisibility = DEFAULT_SYSTEM_UI_VISIBILITY
        resId?.let {
            if(context != null) {
                statusBarColor = context.getColorCompat(it)
            }
        }
    }
}

fun Window.statusBarHeight(): Int {
    val rectangle = Rect()
    decorView.getWindowVisibleDisplayFrame(rectangle)
    return rectangle.top
}

const val DEFAULT_SYSTEM_UI_VISIBILITY = 0