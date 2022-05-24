package com.yaksonn.marveltutorial.core.util.extensions

internal fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}