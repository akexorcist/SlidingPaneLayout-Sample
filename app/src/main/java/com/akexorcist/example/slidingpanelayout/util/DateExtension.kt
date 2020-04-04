package com.akexorcist.example.slidingpanelayout.util

import java.text.SimpleDateFormat
import java.util.*

internal fun String.toDate(): String? {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault()).parse(this)?.let { date ->
        SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(date)
    } ?: run {
        null
    }
}