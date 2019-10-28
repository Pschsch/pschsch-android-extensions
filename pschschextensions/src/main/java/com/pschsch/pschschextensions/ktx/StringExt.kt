package com.pschsch.pschschextensions.ktx

fun String?.isNotNullOrEmpty() = !this.isNullOrEmpty()

fun String?.isNullOrEmptyDeep() = this == "null" || this.isNullOrEmpty()

fun String?.isNotNullOrEmptyDeep() = !this.isNullOrEmptyDeep()