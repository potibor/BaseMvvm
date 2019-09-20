package com.hsnozan.basemvvm.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.hsnozan.basemvvm.BaseApplication
import com.hsnozan.basemvvm.api.BaseService
import com.hsnozan.basemvvm.core.BaseViewModel
import com.hsnozan.basemvvm.model.LoadingState

class LoginViewModel(
    var baseApplication: BaseApplication,
    var baseService: BaseService
) : BaseViewModel(baseApplication) {

    var progressVisibility = ObservableField(false)
    var loadingState: MutableLiveData<LoadingState> = MutableLiveData()

    fun sendLogin(userName: String?, password: String?) {

    }
}
