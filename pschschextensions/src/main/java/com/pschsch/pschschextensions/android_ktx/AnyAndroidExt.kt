package com.pschsch.pschschextensions.android_ktx

import android.util.Log

fun Any.log(msg: String, priority: Int = Log.DEBUG) =
    Log.println(priority, this::class.java.simpleName, msg)