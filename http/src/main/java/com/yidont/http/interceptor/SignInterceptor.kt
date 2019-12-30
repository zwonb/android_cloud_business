package com.yidont.http.interceptor

import com.yidont.http.upload.TextBody
import com.yidont.lib.extension.aesEncrypt
import com.yidont.lib.extension.getAndroidId
import com.yidont.lib.provider.appContext
import com.yidont.lib.util.SPUtil
import com.yidont.lib.util.getVersionCode
import okhttp3.*

/**
 * 签名校验拦截器
 *
 * @author zwonb
 * @date 2019-12-13
 */
class SignInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = when (request.method()) {
            "POST" -> httpPostIntercept(request)
            "GET" -> appendGetParameter(request)
            else -> request
        }
        return chain.proceed(request)
    }

    private fun httpPostIntercept(request: Request): Request {
        return when (val requestBody = request.body()) {
            is FormBody -> appendFormBody(requestBody, request)
            is MultipartBody -> appendMultipartBody(requestBody, request)
            else -> request
        }
    }

    private fun appendFormBody(requestBody: FormBody, request: Request): Request {
        val builder = requestBody.newBuilder()
        SPUtil.getUid().apply {
            if (isNotEmpty()) {
                builder.add("uid", this)
            }
        }
        builder.add("versionCode", getVersionCode())
        builder.add("sim", appContext.getAndroidId().aesEncrypt())
//        builder.add("accessSign", sign(builder.build()))
        return request.newBuilder()
            .post(builder.build())
            .build()
    }

    private fun appendMultipartBody(
        requestBody: MultipartBody,
        request: Request
    ): Request {
        val builder = requestBody.newBuilder(requestBody.boundary())
        SPUtil.getUid().apply {
            if (isNotEmpty()) {
                builder.addTextBody("uid", this)
            }
        }
        builder.addTextBody("versionCode", getVersionCode())
        builder.addTextBody("sim", appContext.getAndroidId().aesEncrypt())
//        builder.addTextBody("accessSign", sign(builder.build().parts()))
        return request.newBuilder()
            .post(builder.build())
            .build()
    }

    private fun appendGetParameter(request: Request): Request {
        val urlBuilder = request.url().newBuilder()
        SPUtil.getUid().apply {
            if (isNotEmpty()) {
                urlBuilder.addQueryParameter("uid", this)
            }
        }
        urlBuilder.addQueryParameter("versionCode", getVersionCode())
        urlBuilder.addQueryParameter("sim", appContext.getAndroidId().aesEncrypt())
//        urlBuilder.addQueryParameter("accessSign", sign(urlBuilder.build()))
        return request.newBuilder()
            .url(urlBuilder.build())
            .build()
    }

    companion object {
        fun FormBody?.newBuilder(): FormBody.Builder {
            val builder = FormBody.Builder()
            if (this == null) {
                return builder
            }
            for (i in 0 until this.size()) {
                builder.add(this.name(i), this.value(i))
            }
            return builder
        }

        fun MultipartBody?.newBuilder(boundary: String): MultipartBody.Builder {
            val builder = MultipartBody.Builder(boundary)
            if (this == null) {
                return builder
            }
            for (part in this.parts()) {
                builder.addPart(part)
            }
            builder.setType(MultipartBody.FORM)
            return builder
        }

        fun MultipartBody.Builder.addTextBody(key: String, value: String) {
            addFormDataPart(key, null, TextBody(key, value))
        }
    }

}