package com.github.dudgns0507.recyclerviewapp.ui.post

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dudgns0507.recyclerviewapp.R
import com.github.dudgns0507.recyclerviewapp.data.dto.Post
import com.github.dudgns0507.recyclerviewapp.databinding.ActivityPostBinding
import com.github.dudgns0507.recyclerviewapp.ui.base.BaseActivity
import com.github.dudgns0507.mvvm_cropo.ui.post.edit.PostEditActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding>() {
    private lateinit var dialog: Dialog
    override val layoutResId = R.layout.activity_post
    override val viewModel: PostViewModel by viewModels()

    private lateinit var commentAdapter: CommentAdapter

    companion object {
        fun callingIntent(context: Context, post: Post): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(BUNDLE_KEY, Gson().toJson(post))
            return intent
        }
    }

    override fun initAfterBinding() {
        viewModel.bundle = Gson().fromJson(intent.getStringExtra(BUNDLE_KEY), Post::class.java)

        dataBinding.apply {
            commentAdapter = CommentAdapter()
            val layoutManager = LinearLayoutManager(this@PostActivity)

            rvComment.layoutManager = layoutManager
            rvComment.setHasFixedSize(true)
            rvComment.adapter = commentAdapter

            btDelete.setOnClickListener {
                dialog = AlertDialog.Builder(this@PostActivity)
                    .setMessage(R.string.check_delete)
                    .setPositiveButton("확인") { _, _ ->
                        viewModel.bundle?.let {
                            viewModel.deletePost(it.id)
                        }
                    }
                    .setNegativeButton("취소") { d, _ ->
                        d.dismiss()
                    }
                    .create()
                dialog.show()
            }

            btEdit.setOnClickListener {
                viewModel.bundle?.let {
                    startActivity(PostEditActivity.callingIntent(this@PostActivity, it))
                }
            }
        }
    }

    override fun initObserve() {
        viewModel.post.observe(this, {
            dataBinding.apply {
                tvTitle.text = it.title
                tvDescription.text = it.body
            }
        })

        viewModel.comments.observe(this, {
            commentAdapter.addAll(it)
        })

        viewModel.deletePost.observe(this, {
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            finish()
        })
    }

    override fun initData() {
        viewModel.bundle?.let {
            viewModel.requestPost(it.id)
            viewModel.requestComments(it.id)
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}