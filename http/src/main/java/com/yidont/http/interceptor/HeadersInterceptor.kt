package com.yidont.http.interceptor

import android.util.Base64
import com.yidont.lib.extension.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * 请求头拦截器
 *
 * @author zwonb
 * @date 2019-12-12
 */
class HeadersInterceptor : Interceptor {

    companion object {
        // 铁通
        const val CLUEID_KEY = "api.tietong.comZhoushouby!@#$^&"
        // 天天电信
//        const val CLUEID_KEY = "http://ceshi.yidont.com/app/Italy/"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val time = headerDateTime()
        val request = chain.request()
            .newBuilder()
//            .addHeader("key", getHeaderKey(time))
//            .addHeader("clueid", getHeaderId(time))
            .build()
        return chain.proceed(request)
    }

    private fun getHeaderKey(headerTime: String): String {
        val encodeToString = Base64.encodeToString(headerTime.toByteArray(), Base64.NO_WRAP)
            .replace("=", "")
        val length = encodeToString.length
        val start = encodeToString.substring(length - 4)
        val center = encodeToString.substring(4, length - 4)
        val end = encodeToString.substring(0, 4)
        return "$start$center$end"
    }

    private fun getHeaderId(time: String): String {
        return "$CLUEID_KEY$time".md5()
    }

    private fun headerDateTime(): String {
        val format = SimpleDateFormat("MMddHHmmss", Locale.CHINESE)
        return format.format(Date())
    }

}