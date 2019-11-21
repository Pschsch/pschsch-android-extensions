package com.pschsch.pschschextensions.android_ktx

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

private val sLock = Any()
private val sTypedValue: TypedValue by lazy { TypedValue() }

fun Activity.hideKeyboard() =
    getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

fun Activity.showKeyboard() {
    getSystemService<InputMethodManager>()?.showSoftInput(
        currentFocus,
        InputMethodManager.SHOW_IMPLICIT
    )
}

/**Rewritten some androidx.core.content.ContextCompat methods as ktx, so Proguard/R8 could shrink ContextCompat methods, if you don't use it**/

fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? {
    return when {
        Build.VERSION.SDK_INT >= 21 -> getDrawable(resId)
        Build.VERSION.SDK_INT >= 16 -> resources.getDrawable(resId)
        else -> {
            val resolvedId: Int
            synchronized(sLock) {
                resources.getValue(resId, sTypedValue, true)
                resolvedId = sTypedValue.resourceId
            }
            resources.getDrawable(resolvedId)
        }
    }
}

fun Context.getColorCompat(@ColorRes resId: Int): Int {
    return if (Build.VERSION.SDK_INT >= 23) {
        getColor(resId)
    } else {
        resources.getColor(resId)
    }
}

fun Context.getColorStateListCompat(@ColorRes resId: Int): ColorStateList? {
    return if (Build.VERSION.SDK_INT >= 23) {
        getColorStateList(resId)
    } else {
        resources.getColorStateList(resId)
    }
}

inline fun <reified T> Context.systemService() : T {
    return getSystemService(T::class.java)
}

fun <T : Number> Context.dpToPx(dp: T) = dp.toFloat() * resources.displayMetrics.density

fun <T : Number> Context.pxToDp(px: T) = px.toFloat() / resources.displayMetrics.density
