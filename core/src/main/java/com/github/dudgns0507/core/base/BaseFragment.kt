package com.github.dudgns0507.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected val TAG: String by lazy {
        javaClass.simpleName.substring(javaClass.simpleName.lastIndexOf(".") + 1)
            .apply {
                if (length > 23) {
                    replace("Fragment", "")
                }
            }
    }

    lateinit var dataBinding: T
    lateinit var act: AppCompatActivity
    lateinit var baseAct: BaseActivity<*>

    abstract val layoutResId: Int
    abstract val viewModel: BaseViewModel

    abstract fun initObserve()
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        act = requireActivity() as AppCompatActivity
        baseAct = requireActivity() as BaseActivity<*>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBinding(inflater, container)
        initAfterBinding()
        initObserve()

        return dataBinding.root
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        dataBinding.apply {
            lifecycleOwner = this@BaseFragment
        }
    }
}