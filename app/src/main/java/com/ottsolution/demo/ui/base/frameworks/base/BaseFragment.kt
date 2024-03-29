package com.ottsolution.demo.ui.base.frameworks.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.ottsolution.demo.ui.base.frameworks.extensions.observe
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {

    abstract fun getViewModelClass(): Class<VM>
    abstract fun layoutId(): Int


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[getViewModelClass()]
        observe(viewModel.errorMessage) { it ->
            it.getContentIfNotHandled()?.let {
                it.message?.let { it1 -> (activity as BaseActivity).alert(it1) }
            }
        }
        observe(viewModel.userMessage){ event ->
            event.getContentIfNotHandled()?.let {
                (activity as BaseActivity).alert(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(layoutId(), container, false)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = this
        return view
    }

    fun navController() = findNavController(view!!)
}