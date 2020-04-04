package com.akexorcist.example.slidingpanelayout.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookSelection(val book: Book, var isShowing: Boolean) : Parcelable