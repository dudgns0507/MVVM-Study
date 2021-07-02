package com.github.dudgns0507.recyclerviewapp.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.recyclerviewapp.data.dto.Comment
import com.github.dudgns0507.recyclerviewapp.databinding.CommentItemBinding
import com.github.dudgns0507.recyclerviewapp.databinding.PostItemBinding

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private val comments = arrayListOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position, comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun addAll(c: List<Comment>) {
        comments.clear()
        comments.addAll(c)
        notifyDataSetChanged()
    }
}