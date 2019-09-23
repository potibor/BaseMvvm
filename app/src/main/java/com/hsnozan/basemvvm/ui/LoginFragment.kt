package com.hsnozan.basemvvm.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.lifecycle.Observer
import com.hsnozan.basemvvm.R
import com.hsnozan.basemvvm.core.BaseFragment
import com.hsnozan.basemvvm.databinding.LoginFragmentBinding
import com.hsnozan.basemvvm.model.State
import com.hsnozan.basemvvm.utils.hideKeyboardFrom
import com.hsnozan.basemvvm.utils.toast
import com.hsnozan.basemvvm.utils.tryCatch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginViewModel, LoginFragmentBinding>() {

    private val loginViewModel: LoginViewModel by viewModel()

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
        observeLoginState()
    }

    private fun initialize() {
        binding.viewmodel = viewModel
    }

    private fun observeLoginState() {
        viewModel.loadingState.observe(this@LoginFragment, Observer {
            viewModel.progressVisibility.set(false)
            when (it.state) {

                State.ERROR -> {
                    showAlertDialog(
                        activity!!, "Title",
                        "OK", DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }, it.errorMessage!!, true
                    )
                }
                State.SUCCESS -> {
                    activity?.hideKeyboardFrom()
                    activity?.toast("helllo")
                    tryCatch(tryBlock = {
                        loginViewModel.select("Itemmm")
                        //findNavController().navigate()
                    })
                }
            }
        })
    }

    override fun getLayoutID(): Int {
        return R.layout.login_fragment
    }

    override fun initViewModelClass(): Class<LoginViewModel> {
        return loginViewModel.javaClass
    }

}
