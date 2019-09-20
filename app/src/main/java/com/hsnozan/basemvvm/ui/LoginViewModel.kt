package com.hsnozan.basemvvm.ui

import android.util.Base64
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.hsnozan.basemvvm.BaseApplication
import com.hsnozan.basemvvm.R
import com.hsnozan.basemvvm.api.BaseService
import com.hsnozan.basemvvm.core.BaseViewModel
import com.hsnozan.basemvvm.model.LoadingState
import com.hsnozan.basemvvm.model.State
import com.hsnozan.basemvvm.utils.tryCatch
import com.hsnozan.basemvvm.utils.workOnBackground
import io.reactivex.disposables.Disposable

class LoginViewModel(
    var baseApplication: BaseApplication,
    var baseService: BaseService
) : BaseViewModel(baseApplication) {

    var progressVisibility = ObservableField(false)
    var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    private var disposable: Disposable? = null
    private var isValidate = false

    fun sendLogin(userName: String?, password: String?) {
        isValidate = true
        if (nullCheckForProperties(strings = arrayOf(userName!!, password!!))) {
            login(userName, password)
        } else {
            loadingState.value = LoadingState(
                State.ERROR, BaseApplication.context.resources.getString(
                    R.string.error_empty_login_fields
                )
            )
        }
    }

    private fun login(userName: String, password: String) {
        progressVisibility.set(true)
        val authHeader = "73ff629743f7eb40d70eec5d8a92bd14"
        disposable = baseService.sendLogin(authHeader)
            .workOnBackground()
            .subscribe(
                { loadingState.value = LoadingState(State.SUCCESS) },
                { error ->
                    loadingState.value =
                        LoadingState(State.ERROR, errorMessage = error.localizedMessage)
                }
            )
    }

    private fun nullCheckForProperties(strings: Array<String>): Boolean {
        for (string in strings) {
            if (!isValidate) break
            when {
                strings.indexOf(string) == 0 -> isValidate = userNameControl(string)
                strings.indexOf(string) == 1 -> isValidate = userPasswordControl(string)
            }
        }
        return isValidate
    }

    @VisibleForTesting
    private fun userPasswordControl(string: String): Boolean {
        if (string.isEmpty()) {
            return false
        }
        return string.length > 3
    }

    @VisibleForTesting
    private fun userNameControl(string: String): Boolean {
        return string.isNotEmpty()
    }

    override fun onCleared() {
        super.onCleared()
        tryCatch(tryBlock = {
            disposable?.takeIf { isValidate }.apply {
                disposable?.dispose()
            }
        })
    }
}
