package com.github.dudgns0507.recyclerviewapp.ui.post

import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.recyclerviewapp.data.dto.Comment
import com.github.dudgns0507.recyclerviewapp.databinding.CommentItemBinding

class CommentViewHolder(private val itemBinding: CommentItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(position: Int, comment: Comment) {
        itemBinding.apply {
            tvBody.text = comment.body
        }
    }
}
