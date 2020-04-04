package com.akexorcist.example.slidingpanelayout.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.example.slidingpanelayout.vo.Book
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_topic.*

class TopicViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(book: Book, isSelected: Boolean, onClick: () -> Unit) {
        buttonTopic.text = book.title
        buttonTopic.isSelected = isSelected
        buttonTopic.setOnClickListener { onClick() }
    }
}
