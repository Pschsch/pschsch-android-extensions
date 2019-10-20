package com.pschsch.pschschextensions

fun Int?.isNullOrZero() : Boolean {
    return this == null || this == 0
}