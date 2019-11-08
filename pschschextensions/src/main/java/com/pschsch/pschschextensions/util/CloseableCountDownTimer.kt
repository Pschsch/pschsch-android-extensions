package com.pschsch.pschschextensions.util

import android.os.Handler
import android.os.Message
import android.os.SystemClock
import com.pschsch.pschschextensions.ktx.orTrue
import com.pschsch.pschschextensions.ktx.orZero
import java.lang.ref.WeakReference

open class CloseableCountDownTimer {

    private var mMillisInFuture: Long = 0
    private var mCountdownInterval: Long = 0
    private var mStopTimeInFuture: Long = 0
    private var mCancelled = false
    private var mHandler: CountDownHandler? = null

    private val msg = 1

    protected open fun onTick(millisUntilFinished: Long) {

    }

    protected open fun onFinish() {

    }

    @Synchronized
    fun cancel() {
        mCancelled = true
        mHandler?.removeMessages(msg)
        mHandler = null
    }

    @Synchronized
    fun start(millisInFuture: Long, countDownInterval: Long): CloseableCountDownTimer {
        mHandler = CountDownHandler(WeakReference(this))
        mMillisInFuture = millisInFuture
        mCountdownInterval = countDownInterval
        mCancelled = false
        if (mMillisInFuture <= 0) {
            onFinish()
            return this
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture
        mHandler?.sendMessage(mHandler?.obtainMessage(msg))
        return this
    }


    class CountDownHandler(private val countDownTimer: WeakReference<CloseableCountDownTimer>) :
        Handler() {
        override fun handleMessage(msg: Message) {

            synchronized(this) {
                val timer = countDownTimer.get() ?: return
                if (timer.mCancelled) return


                val millisLeft =
                    timer.mStopTimeInFuture - SystemClock.elapsedRealtime()

                if (millisLeft <= 0) {
                    timer.onFinish()
                } else {
                    val lastTickStart = SystemClock.elapsedRealtime()
                    timer.onTick(millisLeft)

                    val lastTickDuration = SystemClock.elapsedRealtime() - lastTickStart
                    var delay: Long

                    if (millisLeft < timer.mCountdownInterval) {

                        delay = millisLeft - lastTickDuration

                        if (delay < 0) delay = 0
                    } else {
                        delay = timer.mCountdownInterval - lastTickDuration


                        while (delay < 0) delay += timer.mCountdownInterval
                    }
                    sendEmptyMessageDelayed(timer.msg, delay)
                }
            }
        }
    }

}