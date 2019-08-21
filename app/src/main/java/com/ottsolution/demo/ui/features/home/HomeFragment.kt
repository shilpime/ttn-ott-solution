package com.ottsolution.demo.ui.features.home


import android.os.Bundle
import android.view.View
import com.ottsolution.demo.R
import com.ottsolution.demo.databinding.FragmentHomeBinding
import com.ottsolution.demo.ui.base.frameworks.base.BaseFragment
import com.ottsolution.demo.ui.base.frameworks.extensions.observe


/**
 * The home screen of the app which displays all the products
 *
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel



    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_home
}
