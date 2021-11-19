package com.nassdk.corecommon.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.convert2ProperPattern(pattern: String = "dd MMMM HH:mm"): String? {

    val decodeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    val date = try {
        decodeFormat.parse(this)
    } catch (e: ParseException) {
        return null
    }

    return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
}