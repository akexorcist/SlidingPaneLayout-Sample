package com.akexorcist.example.slidingpanelayout.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    @SerializedName("title")
    val title: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("publishedDate")
    val publishedDate: Date?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("shortDescription")
    val shortDescription: String?,
    @SerializedName("longDescription")
    val longDescription: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("categories")
    val categories: List<String>?
) : Parcelable {
    @Parcelize
    data class Date(
        @SerializedName("\$date")
        val date: String
    ) : Parcelable
}
