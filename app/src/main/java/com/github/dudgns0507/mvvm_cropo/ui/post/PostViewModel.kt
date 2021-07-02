package com.github.dudgns0507.recyclerviewapp.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.recyclerviewapp.data.dto.Comment
import com.github.dudgns0507.recyclerviewapp.data.dto.Post
import com.github.dudgns0507.recyclerviewapp.data.repository.DataRepository
import com.github.dudgns0507.recyclerviewapp.ui.base.BaseViewModel
import com.github.dudgns0507.recyclerviewapp.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {
    var bundle: Post? = null

    val deletePost = SingleEvent<String>()

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    fun requestPost(id: Int) = viewModelScope.launch {
        val response = dataRepository.requestPost(id)

        response.getData()?.let { result ->
            result.value.let {
                _post.postValue(it)
            }
        }
    }

    fun requestComments(id: Int) = viewModelScope.launch {
        val response = dataRepository.requestPostComments(id)

        response.getData()?.let { result ->
            result.value.let {
                _comments.postValue(it)
            }
        }
    }

    fun deletePost(id: Int) = viewModelScope.launch {
        val response = dataRepository.deletePost(id)
        deletePost.postValue("")
    }
}