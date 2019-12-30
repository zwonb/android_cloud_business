package com.yidont.login.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.yidont.lib.util.KeyValue
import com.yidont.login.ui.InputCodeUIA

/**
 * 注册-验证码
 *
 * @author zwonb
 * @date 2019-12-18
 */
class RegisterCodeUIA : InputCodeUIA() {

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.style = "userRegister"
        super.initView(savedInstanceState)

        viewModel.checkCode.observe(this, Observer {
            val intent = Intent(this, RegisterPwdUIA::class.java)
            intent.putExtra(KeyValue.PHONE, viewModel.phone)
            startActivity(intent)
            finish()
        })
    }
}