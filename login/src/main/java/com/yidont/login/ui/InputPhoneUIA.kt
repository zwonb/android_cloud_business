package com.yidont.login.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.yidont.lib.base.BaseHeadBarUIA
import com.yidont.lib.util.KeyValue
import com.yidont.login.R
import com.yidont.login.data.repository.LoginRepository
import kotlinx.android.synthetic.main.fragment_input_phone.*

/**
 * 注册、忘记密码-输入手机号码
 *
 * @author zwonb
 * @date 2019-12-17
 */
abstract class InputPhoneUIA : BaseHeadBarUIA(R.layout.fragment_input_phone) {

    val viewModel by viewModels<LoginViewModel> { LoginModelFactory(LoginRepository()) }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.phone = intent.getStringExtra(KeyValue.PHONE) ?: ""
        phone_execute.setOnClickListener {
            viewModel.getCode(phone_et.text.toString())
        }

        viewModel.isLoading.observe(this, Observer {
            headBarLoadStatus(it)
            phone_execute.isEnabled = !it
        })
    }

}