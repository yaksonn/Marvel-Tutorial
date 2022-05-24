package com.yaksonn.marveltutorial.common.ext

import android.util.Log

fun runSafely(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        Log.d("Exception: ",e.message.toString())
    }
}