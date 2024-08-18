package com.szeroka.checklist.utils

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultInterval: Int = 500,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.setSafeOnClickListener(duration: Int = 500, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(defaultInterval = duration) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}