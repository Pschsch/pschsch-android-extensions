package com.pschsch.pschschextensions.ktx

fun Boolean?.orTrue() : Boolean = this ?: true

fun Boolean?.orFalse() : Boolean = this ?: false