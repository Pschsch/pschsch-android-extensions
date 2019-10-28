package com.pschsch.pschschextensions.ktx

import java.lang.NumberFormatException

fun Int?.isNullOrZero() = this == null || this == 0

fun Int?.orZero() = this ?: 0

fun Int.Companion.safeParseInt(value: String, defValue: Int = 0): Int {
    return try {
        Integer.parseInt(value)
    } catch (e: NumberFormatException) {
        defValue
    }
}

fun Long?.isNullOrZero() = this == null || this == 0L

fun Long?.orZero() = this ?: 0L

fun Long.Companion.safeParseLong(value: String, defValue: Long = 0L): Long {
    return try {
        java.lang.Long.parseLong(value)
    } catch (e: NumberFormatException) {
        defValue
    }
}

fun Float?.isNullOrZero() = this == null || this == 0f

fun Float?.orZero() = this ?: 0f

fun Float.Companion.safeParseFloat(value: String, defValue: Float = 0f): Float {
    return try {
        java.lang.Float.parseFloat(value)
    } catch (e: NumberFormatException) {
        defValue
    }
}

fun Float.isInteger() = this % 1 == 0f

fun Double?.isNullOrZero() = this == null || this == 0.0

fun Double?.orZero() = this ?: 0.0

fun Double.Companion.safeParseDouble(value: String, defValue: Double = 0.0): Double {
    return try {
        java.lang.Double.parseDouble(value)
    } catch (e: NumberFormatException) {
        defValue
    }
}

fun Double.isInteger() = this % 1 == 0.0