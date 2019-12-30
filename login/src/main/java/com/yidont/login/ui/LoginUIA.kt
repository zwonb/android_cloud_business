package com.yidont.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.yidont.lib.base.BaseHeadBarUIA
import com.yidont.lib.util.KeyValue
import com.yidont.login.R
import com.yidont.login.data.repository.LoginRepository
import com.yidont.login.ui.forget.ForgetPhoneUIA
import com.yidont.login.ui.register.RegisterPhoneUIA
import com.zwonb.headbar.HeadBar
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * 登录
 *
 * @author zwonb
 * @date 2019-12-16
 */
class LoginUIA : BaseHeadBarUIA(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel> { LoginModelFactory(LoginRepository()) }

    override fun headBar(headBar: HeadBar) {
        headBar.setTitle("登录")
    }

    override fun initView(savedInstanceState: Bundle?) {
        login_execute.setOnClickListener {
            viewModel.login(login_phone.text.toString(), login_pwd.text.toString())
        }
        login_register.setOnClickListener {
            startAct(RegisterPhoneUIA::class.java)
        }
        login_forget.setOnClickListener {
            startAct(ForgetPhoneUIA::class.java)
        }

        viewModel.isLoading.observe(this, Observer {
            headBarLoadStatus(it)
            login_execute.isEnabled = !it
        })
        viewModel.login.observe(this, Observer {
            val intent = Intent()
            intent.setClassName(this, KeyValue.MAIN_UIA)
            startActivity(intent)
            finish()
        })
    }

}