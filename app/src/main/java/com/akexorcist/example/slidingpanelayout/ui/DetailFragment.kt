package com.akexorcist.example.slidingpanelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.akexorcist.example.slidingpanelayout.R
import com.akexorcist.example.slidingpanelayout.util.ShortenDrawableRequestListener
import com.akexorcist.example.slidingpanelayout.util.toDate
import com.akexorcist.example.slidingpanelayout.vo.Book
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentBookLiveData.observe(viewLifecycleOwner, bookObserver)
        buttonSelectBook.setOnClickListener { openBooksPane() }
        showEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.currentBookLiveData.removeObserver(bookObserver)
    }

    private val bookObserver = Observer<Book> { book ->
        updateBookDetail(book)
        showContent()
    }

    private val thumbnailRequestListener = object : ShortenDrawableRequestListener() {
        override fun onLoadCompleted(isSuccess: Boolean) {
            hideThumbnailLoading()
        }
    }

    private fun updateBookDetail(book: Book) {
        textViewIsbn.text = book.isbn
        textViewTitle.text = book.title
        textViewAuthors.text = book.authors?.joinToString() ?: ""
        textViewPageCount.text = getString(R.string.page_count, book.pageCount)
        textViewPublishedDate.text = book.publishedDate?.date?.toDate() ?: ""
        textViewDescription.text = when {
            book.longDescription != null -> getString(R.string.book_description, book.longDescription)
            book.shortDescription != null -> getString(R.string.book_description, book.shortDescription)
            else -> getString(R.string.no_book_description)
        }
        textViewCategories.text = book.categories?.joinToString() ?: ""
        showThumbnailLoading()
        Glide.with(this)
            .load(book.thumbnailUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_broken_image)
            .addListener(thumbnailRequestListener)
            .into(imageViewThumbnail)
    }

    private fun openBooksPane() {
        viewModel.openBooksPane()
    }

    private fun showThumbnailLoading() {
        progressBarThumbnail.visibility = View.VISIBLE
    }

    private fun hideThumbnailLoading() {
        progressBarThumbnail.visibility = View.GONE
    }

    private fun showContent() {
        layoutContent.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
    }

    private fun showEmpty() {
        layoutContent.visibility = View.GONE
        layoutEmpty.visibility = View.VISIBLE
    }
}