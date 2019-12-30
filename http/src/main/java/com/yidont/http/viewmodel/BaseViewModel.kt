package com.yidont.http.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yidont.http.data.HttpResult
import com.yidont.lib.util.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ViewModel 基类
 *
 * @author zwonb
 * @date 2019-12-05
 */
open class BaseViewModel : ViewModel() {

    val httpResultOther by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<HttpResult.Other<*>>()
    }

    fun tryLaunch(
        block: suspend CoroutineScope.() -> Unit,
        error: (suspend (Exception) -> Unit)? = null
    ) = viewModelScope.launch {
        try {
            block()
        } catch (e: Exception) {
            error?.also {
                it(e)
            } ?: showToast(e.message)
//                .also {
//                    Log.e("zwonb", "tryLaunch: ${e.message}")
//                }
        }
    }

//    /**
//     * 判断ydCode
//     */
//    protected fun <T : Any> checkCode(
//        response: BaseResponse<T>,
//        success: MutableLiveData<T>? = null,
//        otherCode: MutableLiveData<BaseResponse<T>>? = null
//    ) = when (response.ydCode) {
//        CODE_SUCCESS -> success?.postValue(response.ydBody)
//        CODE_TOAST -> {
//            otherCode?.also {
//                it.postValue(response)
//            } ?: showToast(response.ydMsg)
//        }
//        CODE_OTHER_LOGIN -> {
//            otherCode?.also {
//                it.postValue(response)
//            } ?: showToast(response.ydMsg)
//        }
//        CODE_DIALOG -> {
//            otherCode?.also {
//                it.postValue(response)
//            } ?: showToast(response.ydMsg)
//        }
//        CODE_NO_DATA -> {
//            otherCode?.also {
//                it.postValue(response)
//            } ?: showToast(response.ydMsg)
//        }
//        else -> showToast("ydCode未定义${response.ydCode}")
//    }
}