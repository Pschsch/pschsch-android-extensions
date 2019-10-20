package com.pschsch.pschschextensions

fun String.toSafeLong(defaultValue: Long = 0): Long {
    return try {
        this.toLong()
    } catch (e: NumberFormatException) {
        defaultValue
    }
}