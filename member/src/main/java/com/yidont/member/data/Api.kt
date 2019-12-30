package com.yidont.member.data

import com.yidont.http.data.model.BaseResponse
import com.yidont.member.data.model.MemberFilterBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author zwonb
 * @date 2019-12-24
 */
interface Api {

    @FormUrlEncoded
    @POST("user/")
    suspend fun searchMember(
        @Field("act") act: String,
        @Field("username") phone: String
    ): BaseResponse<List<MemberFilterBean>>

}