package com.yidont.login.data

import com.yidont.http.data.model.BaseResponse
import com.yidont.login.data.model.LoginInfoBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 登陆 api
 * @author zwonb
 * @date 2019-12-05
 */
interface Api {

    @FormUrlEncoded
    @POST("seller/")
    suspend fun login(
        @Field("username") account: String,
        @Field("password") pwd: String,
        @Field("act") act: String
    ): BaseResponse<LoginInfoBean>


    @FormUrlEncoded
    @POST("seller/")
    suspend fun getCode(
        @Field("username") phone: String,
        @Field("style") style: String,
        @Field("act") act: String
    ): BaseResponse<Any>

    @FormUrlEncoded
    @POST("seller/")
    suspend fun checkCode(
        @Field("username") phone: String,
        @Field("code") code: String,
        @Field("style") style: String,
        @Field("act") act: String
    ): BaseResponse<Any>

    @FormUrlEncoded
    @POST("seller/")
    suspend fun checkPwd(
        @Field("username") phone: String,
        @Field("password") pwd0: String,
        @Field("rePassword") pwd1: String,
        @Field("style") style: String,
        @Field("act") act: String
    ): BaseResponse<Any>
}