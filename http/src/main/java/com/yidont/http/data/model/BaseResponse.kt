package com.yidont.http.data.model

import com.yidont.http.data.HttpResult

/**
 * 网络请求返回的数据基类
 *
 * @author zwonb
 * @date 2019-12-05
 */
data class BaseResponse<out T : Any>(val ydCode: String, val ydMsg: String, val ydBody: T) {

    fun result() = when (ydCode) {
        CODE_SUCCESS -> HttpResult.Success(ydBody)
//        CODE_TOAST -> HttpResult.Toast(ydMsg)
//        CODE_OTHER_LOGIN -> HttpResult.OtherLogin(ydMsg)
//        CODE_DIALOG -> HttpResult.Dialog(ydMsg)
//        CODE_NO_DATA -> HttpResult.NoData(ydMsg)
        else -> HttpResult.Other(this)
    }
}

const val CODE_SUCCESS = "100"
const val CODE_TOAST = "101"
const val CODE_OTHER_LOGIN = "102"
const val CODE_DIALOG = "103"
const val CODE_NO_DATA = "104"