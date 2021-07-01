package com.github.dudgns0507.mvvm_cropo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.core.util.SingleEvent
import com.github.dudgns0507.mvvm_cropo.data.model.Post
import com.github.dudgns0507.mvvm_cropo.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    state: SavedStateHandle,
    private val dataRepository: DataRepository
) : BaseViewModel(state) {
    val openPostDetail = SingleEvent<Post>()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private var count = 0

    fun requestPosts() = viewModelScope.launch {
        val response = dataRepository.requestPosts(count, 10)

        response.getData()?.let { result ->
            result.value.let {
                val temp = it.toMutableList()
                temp.addAll(it)
                _posts.postValue(temp)
            }
        }
    }

    fun loadMore() {
        count.plus(1)
        requestPosts()
    }

    fun loadFirst() {
        count = 0
        _posts.postValue(arrayListOf())
        requestPosts()
    }

    fun openPostDetail(post: Post) {
        openPostDetail.postValue(post)
    }
}