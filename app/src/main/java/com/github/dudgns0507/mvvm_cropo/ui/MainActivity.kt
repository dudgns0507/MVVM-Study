package com.github.dudgns0507.mvvm_cropo.ui

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.util.ext.moveTo
import com.github.dudgns0507.core.util.ext.observe
import com.github.dudgns0507.core.util.ext.toast
import com.github.dudgns0507.mvvm_cropo.R
import com.github.dudgns0507.mvvm_cropo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainBundle(val t: String) : Parcelable

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainBundle, MainViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun MainViewModel.load() {
        requestData()
    }

    override fun MainViewModel.regist() {
        observe(posts) {
            toast(it)
        }
    }

    override fun ActivityMainBinding.bind() {
        initSection()
    }

    fun ActivityMainBinding.initSection() {
        tvTitle.text = "sfddsf"

        moveTo<MainActivity>(MainBundle(t = ""))
        moveTo<MainActivity>()
    }
}