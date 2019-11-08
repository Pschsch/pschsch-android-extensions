package com.pschsch.pschschextensions.android_ktx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pschsch.pschschextensions.util.CloseableCountDownTimer

inline fun broadCastReceiver(crossinline receive: (Context, Intent) -> Unit): BroadcastReceiver {
    return object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context != null && intent != null) {
                receive(context, intent)
            }
        }
    }
}

inline fun closeableCountDownTimerOnFinishOnly(crossinline finish: () -> Unit): CloseableCountDownTimer {
    return object : CloseableCountDownTimer() {
        override fun onFinish() {
            finish()
        }
    }
}

inline fun closeableCountDownTimer(
    crossinline finish: () -> Unit,
    crossinline tick: (Long) -> Unit
): CloseableCountDownTimer {
    return object : CloseableCountDownTimer() {
        override fun onTick(millisUntilFinished: Long) {
            tick(millisUntilFinished)
        }

        override fun onFinish() {
            finish()
        }
    }
}
