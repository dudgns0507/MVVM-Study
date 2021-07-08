package com.github.dudgns0507.mvvm_cropo.ui.post

import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.mvvm_cropo.data.model.Comment
import com.github.dudgns0507.mvvm_cropo.databinding.CommentItemBinding

class CommentViewHolder(private val itemBinding: CommentItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(position: Int, comment: Comment) {
        itemBinding.apply {
            tvBody.text = comment.body
        }
    }
}
