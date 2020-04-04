package com.akexorcist.example.slidingpanelayout.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akexorcist.example.slidingpanelayout.vo.Book

class BookViewModel : ViewModel() {
    private val _booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val booksLiveData: LiveData<List<Book>> = _booksLiveData

    private val _currentBookLiveData: MutableLiveData<Book> = MutableLiveData()
    val currentBookLiveData: LiveData<Book> = _currentBookLiveData

    private val _openBooksPaneLiveData: MutableLiveData<Unit> = MutableLiveData()
    val openBooksPaneLiveData: LiveData<Unit> = _openBooksPaneLiveData

    private val _closeBooksPaneLiveData: MutableLiveData<Unit> = MutableLiveData()
    val closeBooksPaneLiveData: LiveData<Unit> = _closeBooksPaneLiveData

    fun setBooks(books: List<Book>) {
        _booksLiveData.value = books
    }

    fun selectBook(book: Book) {
        _currentBookLiveData.value = book
    }

    fun openBooksPane() {
        _openBooksPaneLiveData.value = Unit
    }

    fun closeBooksPane() {
        _closeBooksPaneLiveData.value = Unit
    }
}