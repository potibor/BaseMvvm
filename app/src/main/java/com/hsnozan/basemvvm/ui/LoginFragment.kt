package com.hsnozan.basemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hsnozan.basemvvm.R
import com.hsnozan.basemvvm.core.BaseFragment
import com.hsnozan.basemvvm.databinding.LoginFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginViewModel, LoginFragmentBinding>() {

    private val loginViewModel : LoginViewModel by viewModel()

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        initial()
    }

    private fun initial() {
        binding.viewmodel = viewModel
    }

    override fun getLayoutID(): Int {
        return R.layout.login_fragment
    }

    override fun initViewModelClass(): Class<LoginViewModel> {
        return loginViewModel.javaClass
    }

}
