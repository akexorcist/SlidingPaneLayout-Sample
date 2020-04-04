package com.akexorcist.example.slidingpanelayout.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.akexorcist.example.slidingpanelayout.R
import com.akexorcist.example.slidingpanelayout.vo.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.openBooksPaneLiveData.observe(this, openBooksPaneObserver)
        viewModel.closeBooksPaneLiveData.observe(this, closeBooksPaneObserver)
        viewModel.setBooks(Mock.getBooks(this))
        openBooksPane()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.openBooksPaneLiveData.removeObserver(openBooksPaneObserver)
        viewModel.closeBooksPaneLiveData.removeObserver(closeBooksPaneObserver)
    }

    override fun onBackPressed() {
        if (slidingPaneLayout.isOpen) {
            super.onBackPressed()
        } else {
            openBooksPane()
        }
    }

    private val openBooksPaneObserver = Observer<Unit> {
        openBooksPane()
    }

    private val closeBooksPaneObserver = Observer<Unit> {
        closeBooksPane()
    }

    private fun openBooksPane() {
        slidingPaneLayout.openPane()
    }

    private fun closeBooksPane() {
        slidingPaneLayout.closePane()
    }
}
