package com.yidont.login.ui

import androidx.lifecycle.MutableLiveData
import com.yidont.http.data.HttpResult
import com.yidont.http.viewmodel.BaseViewModel
import com.yidont.lib.extension.aesEncrypt
import com.yidont.lib.util.SPUtil
import com.yidont.lib.util.isPhone
import com.yidont.lib.util.showToast
import com.yidont.login.data.model.LoginInfoBean
import com.yidont.login.data.repository.LoginRepository

/**
 * 登陆 ViewModule
 *
 * @author zwonb
 * @date 2019-12-05
 */
class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {

    var phone = "xxxxxxxxxxx"
    // 注册、找回密码类型
    var style = ""
    val isLoading = MutableLiveData<Boolean>()
    val getCode = MutableLiveData<Any>()
    val checkCode = MutableLiveData<Any>()
    val checkPwd = MutableLiveData<Any>()
    val pwdStatus = MutableLiveData<Boolean>()
    val login = MutableLiveData<LoginInfoBean>()

    val codeCountDown = MutableLiveData<Long>()

    fun login(phone: String, pwd: String) {
        if (!checkLogin(phone, pwd)) {
            return
        }
        isLoading.value = true
        tryLaunch({
            val response = repository.login(phone, pwd)
            isLoading.postValue(false)
            when (val result = response.result()) {
                is HttpResult.Success -> {
                    SPUtil.putUid(result.data.uid)
                    SPUtil.putPhone(phone.aesEncrypt())
                    login.postValue(result.data)
                }
                else -> showToast(response.ydMsg)
            }
        }, {
            isLoading.postValue(false)
            showToast(it.message)
        })
    }

    /* 获取验证码 */
    fun getCode(phone: String) {
        if (!checkPhone(phone)) {
            return
        }
        isLoading.postValue(true)
        tryLaunch({
            val response = repository.getCode(phone, style)
            isLoading.postValue(false)
            when (val result = response.result()) {
                is HttpResult.Success -> {
                    this@LoginViewModel.phone = phone
                    getCode.postValue(result.data)
                }
                else -> showToast(response.ydMsg)
            }
        }, {
            isLoading.postValue(false)
            showToast(it.message)
        })
    }

    /* 校验验证码 */
    fun checkCode(code: String) {
        if (code.isBlank()) {
            showToast("请输入验证码")
            return
        }
        isLoading.postValue(true)
        tryLaunch({
            val response = repository.checkCode(phone, code, style)
            isLoading.postValue(false)
            when (val result = response.result()) {
                is HttpResult.Success -> {
                    checkCode.postValue(result.data)
                }
                else -> showToast(response.ydMsg)
            }
        }, {
            isLoading.postValue(false)
            showToast(it.message)
        })
    }

    /* 输入密码完成 */
    fun checkPwd(pwd0: String, pwd1: String) {
        if (!inputPwd(pwd0, pwd1)) {
            return
        }
        isLoading.postValue(true)
        tryLaunch({
            val response = repository.checkPwd(phone, pwd0, pwd1, style)
            isLoading.postValue(false)
            when (val result = response.result()) {
                is HttpResult.Success -> {
                    checkPwd.postValue(result.data)
                }
                else -> showToast(response.ydMsg)
            }
        }, {
            isLoading.postValue(false)
            showToast(it.message)
        })
    }

    private fun checkLogin(phone: String, pwd: String): Boolean {
        return checkPhone(phone) && checkPwd(pwd)
    }

    private fun checkPhone(phone: String) = if (!phone.isPhone()) {
        showToast("请输入11位有效手机号码")
        false
    } else {
        true
    }

    private fun inputPwd(pwd0: String, pwd1: String): Boolean {
        if (!checkPwd(pwd0) || !checkPwd(pwd1)) {
            return false
        }
        if (pwd0 != pwd1) {
            showToast("密码不一致")
            return false
        }
        return true
    }

    private fun checkPwd(pwd: String): Boolean {
        if (pwd.isBlank()) {
            showToast("密码不能为空")
            return false
        }
        if (pwd.length < 6) {
            showToast("密码不能少于6位")
            return false
        }
        return true
    }

}