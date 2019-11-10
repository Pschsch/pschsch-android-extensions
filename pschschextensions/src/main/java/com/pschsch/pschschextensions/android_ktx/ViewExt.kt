package com.pschsch.pschschextensions.android_ktx

import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

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
