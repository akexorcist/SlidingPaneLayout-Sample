package com.akexorcist.example.slidingpanelayout.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.example.slidingpanelayout.R
import com.akexorcist.example.slidingpanelayout.vo.Book

class TopicAdapter : RecyclerView.Adapter<TopicViewHolder>() {
    private var books: List<Book>? = null
    private var selectedIndex = -1
    private var onTopicClick: ((Book, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder =
        TopicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_topic, parent, false))

    override fun getItemCount(): Int = books?.size ?: 0

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        books?.get(position)?.let { book ->
            holder.bind(book, selectedIndex == position) {
                onTopicClick?.invoke(book, position)
            }
        }
    }

    fun updateBookSelections(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    fun updateSelectedBookPosition(index: Int) {
        val previousSelectedIndex = this.selectedIndex
        this.selectedIndex = index
        notifyItemChanged(previousSelectedIndex)
        notifyItemChanged(index)
    }

    fun setTopicClickListener(onTopicClick: ((Book, Int) -> Unit)?) {
        this.onTopicClick = onTopicClick
    }
}
