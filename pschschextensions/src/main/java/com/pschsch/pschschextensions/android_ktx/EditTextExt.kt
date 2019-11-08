package com.pschsch.pschschextensions.android_ktx

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText

inline fun EditText.afterTextChanged(crossinline atc: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s != null)
                atc(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

inline fun EditText.onTextChanged(crossinline otc: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != null)
                otc(s)
        }
    })
}

inline fun EditText.beforeTextChanged(crossinline otc: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (s != null)
                otc(s)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.setReadOnly(readOnly: Boolean, inputType: Int = InputType.TYPE_NULL) {
    isFocusable = !readOnly
    isFocusableInTouchMode = !readOnly
    this.inputType = inputType
}