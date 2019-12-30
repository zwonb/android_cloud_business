package com.yidont.login.ui

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.yidont.lib.base.BaseHeadBarUIA
import com.yidont.lib.util.KeyValue
import com.yidont.login.R
import com.yidont.login.data.repository.LoginRepository
import kotlinx.android.synthetic.main.fragment_input_pwd.*

/**
 * 输入密码
 *
 * @author zwonb
 * @date 2019-12-17
 */
abstract class InputPwdUIA : BaseHeadBarUIA(R.layout.fragment_input_pwd) {

    val viewModel by viewModels<LoginViewModel> { LoginModelFactory(LoginRepository()) }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.phone = intent.getStringExtra(KeyValue.PHONE) ?: ""
        pwd_execute.setOnClickListener {
            viewModel.checkPwd(pwd_et1.text.toString(), pwd_et2.text.toString())
        }
        viewModel.isLoading.observe(this, Observer {
            headBarLoadStatus(it)
            pwd_execute.isEnabled = !it
        })

        pwd_img1.setOnClickListener {
            viewModel.pwdStatus.value = !(viewModel.pwdStatus.value ?: false)
        }
        pwd_img2.setOnClickListener {
            viewModel.pwdStatus.value = !(viewModel.pwdStatus.value ?: false)
        }
        viewModel.pwdStatus.observe(this, Observer {
            editStatus(it)
        })
    }

    private fun editStatus(status: Boolean) {
        if (status) {
            pwd_et1.transformationMethod = HideReturnsTransformationMethod.getInstance()
            pwd_et2.transformationMethod = HideReturnsTransformationMethod.getInstance()

            val theme = ContextCompat.getColor(this, R.color.theme)
            pwd_img1.setColorFilter(theme)
            pwd_img2.setColorFilter(theme)
        } else {
            pwd_et1.transformationMethod = PasswordTransformationMethod.getInstance()
            pwd_et2.transformationMethod = PasswordTransformationMethod.getInstance()

            val theme = ContextCompat.getColor(this, R.color.line_ccc)
            pwd_img1.setColorFilter(theme)
            pwd_img2.setColorFilter(theme)
        }
        selectEdit()
    }

    private fun selectEdit() {
        currentFocus?.also {
            if (it is EditText) {
                it.text.length.also { length ->
                    it.setSelection(length)
                }
            }
        }
    }
}