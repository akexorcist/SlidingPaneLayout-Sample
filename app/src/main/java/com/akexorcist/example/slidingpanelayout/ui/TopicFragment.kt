package com.akexorcist.example.slidingpanelayout.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akexorcist.example.slidingpanelayout.R
import com.akexorcist.example.slidingpanelayout.ui.adapter.TopicAdapter
import com.akexorcist.example.slidingpanelayout.vo.Book
import kotlinx.android.synthetic.main.fragment_topic.*

class TopicFragment : Fragment() {
    private val viewModel: BookViewModel by activityViewModels()
    private val adapter = TopicAdapter()
    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context).apply {
            isSmoothScrollbarEnabled = true
        }
    }

    private var selectedBookPosition: Int = -1

    companion object {
        private const val EXTRA_SELECTED_BOOK_POSITION = "extra_selected_book_position"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.booksLiveData.observe(viewLifecycleOwner, booksObserver)
        adapter.setTopicClickListener(onTopicClick)
        recyclerViewTopic.layoutManager = layoutManager
        recyclerViewTopic.adapter = adapter
        showEmpty()

        if (savedInstanceState != null) {
            this.selectedBookPosition = savedInstanceState.getInt(EXTRA_SELECTED_BOOK_POSITION)
            adapter.updateSelectedBookPosition(this.selectedBookPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.booksLiveData.removeObserver(booksObserver)
    }

    private val booksObserver = Observer<List<Book>> { books ->
        showContent()
        adapter.updateBookSelections(books)
    }

    private val onTopicClick: (Book, Int) -> Unit = { book, position ->
        this.selectedBookPosition = position
        adapter.updateSelectedBookPosition(position)
        layoutManager.scrollToPositionWithOffset(position, 0)
        onBookSelected(book)
    }

    private fun onBookSelected(book: Book) {
        Handler().postDelayed({
            viewModel.selectBook(book)
            viewModel.closeBooksPane()
        }, 300)
    }

    private fun showContent() {
        layoutContent.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
    }

    private fun showEmpty() {
        layoutContent.visibility = View.GONE
        layoutEmpty.visibility = View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_SELECTED_BOOK_POSITION, selectedBookPosition)
    }
}
