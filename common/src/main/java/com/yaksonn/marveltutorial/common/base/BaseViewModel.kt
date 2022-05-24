package com.yaksonn.marveltutorial.common.base

import android.util.Log
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val TAG = this.toString().split(".").lastOrNull()
    init {
        Log.e("$TAG","init")
    }

    override fun onCleared() {
        Log.e("$TAG","onCleared")
        super.onCleared()
    }
}