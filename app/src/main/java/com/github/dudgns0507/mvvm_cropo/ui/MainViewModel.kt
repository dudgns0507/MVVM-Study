package com.github.dudgns0507.mvvm_cropo.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.github.dudgns0507.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(state: SavedStateHandle) : BaseViewModel(state) {
    private val _posts = MutableLiveData<String>()
    val posts: LiveData<String> = _posts

    fun requestData() {
        _posts.postValue("테스트테스")
    }
}