package com.yaksonn.marveltutorial.core.util.extensions

import java.security.MessageDigest

internal fun String.toMD5() =
    MessageDigest
        .getInstance("MD5")
        .digest(toByteArray())
        .toHex()