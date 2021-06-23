package com.github.dudgns0507.mvvm_cropo.ui

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.mvvm_cropo.R
import com.github.dudgns0507.mvvm_cropo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun initObserve() {
    }

    override fun initData() {
    }

    override fun initAfterBinding() {
    }
}