package com.akexorcist.example.slidingpanelayout.vo

import android.content.Context
import com.google.gson.Gson
import java.io.InputStream

object Mock {
    fun getBooks(context: Context): List<Book> {
        val inputStream: InputStream = context.assets.open("books.json")
        return Gson().fromJson(inputStream.reader(), Array<Book>::class.java).toList()
    }
}