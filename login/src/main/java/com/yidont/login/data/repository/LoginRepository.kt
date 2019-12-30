package com.yidont.login.data.repository

import com.yidont.http.httpCreate
import com.yidont.lib.extension.aesEncrypt
import com.yidont.login.data.Api

/**
 * @author zwonb
 * @date 2019-12-05
 */
class LoginRepository {

    suspend fun login(phone: String, pwd: String) =
        httpCreate<Api>().login(phone.aesEncrypt(), pwd.aesEncrypt(), "userLogin")

    suspend fun getCode(phone: String, style: String) =
        httpCreate<Api>().getCode(phone.aesEncrypt(), style, "getCode")

    suspend fun checkCode(phone: String, code: String, style: String) =
        httpCreate<Api>().checkCode(phone.aesEncrypt(), code, style, "checkCode")

    suspend fun checkPwd(phone: String, pwd0: String, pwd1: String, style: String) =
        httpCreate<Api>().checkPwd(
            phone.aesEncrypt(),
            pwd0.aesEncrypt(),
            pwd1.aesEncrypt(),
            style,
            "userRegister"
        )
}