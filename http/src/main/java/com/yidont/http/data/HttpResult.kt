package com.yidont.http.data

import com.yidont.http.data.model.BaseResponse

/**
 * 网络请求结果
 *
 * @author zwonb
 * @date 2019-12-11
 */
sealed class HttpResult<out T> {

    data class Success<out T : Any>(val data: T) : HttpResult<T>()
    data class Other<out T : Any>(val response: BaseResponse<T>) : HttpResult<T>()
//    data class Toast(val msg: String) : HttpResult<Nothing>()
//    data class OtherLogin(val msg: String) : HttpResult<Nothing>()
//    data class Dialog(val msg: String) : HttpResult<Nothing>()
//    data class NoData(val msg: String) : HttpResult<Nothing>()
//    data class Unknown(val data: BaseResponse<Any>) : HttpResult<Nothing>()
//    data class Error(val exception: Exception) : HttpResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> data.toString()
            is Other -> response.toString()
//            is Toast -> msg
//            is OtherLogin -> msg
//            is Dialog -> msg
//            is NoData -> msg
//            is Unknown -> data.ydCode
//            is Error -> exception.message ?: "请求出错"
        }
    }
}