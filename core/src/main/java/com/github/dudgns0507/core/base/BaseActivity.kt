package com.github.dudgns0507.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    companion object {
        const val BUNDLE_KEY = "data"
        protected val TAG: String by lazy {
            javaClass.simpleName.substring(javaClass.simpleName.lastIndexOf(".") + 1)
                .apply {
                    if (length > 23) {
                        replace("Activity", "")
                    }
                }
        }
    }

    lateinit var dataBinding: T

    abstract val layoutResId: Int
    abstract val viewModel: BaseViewModel

    abstract fun initObserve()
    abstract fun initData()
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initAfterBinding()
        initObserve()
        initData()
    }

    private fun initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, layoutResId)
        dataBinding.apply {
            lifecycleOwner = this@BaseActivity
        }
    }
}