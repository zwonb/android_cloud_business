package com.yidont.http

import com.yidont.http.interceptor.HeadersInterceptor
import com.yidont.http.interceptor.SignInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author zwonb
 * @date 2019-12-04
 */

const val HTTP_BASE_PATH = ""

object ServiceCreator {

    private const val BASE_URL = "http://api.023yu.cn:8888/APISET/android/"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HeadersInterceptor())
        .addInterceptor(SignInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())

    val retrofit: Retrofit = builder.build()

}

inline fun <reified T> httpCreate(): T = ServiceCreator.retrofit.create(T::class.java)