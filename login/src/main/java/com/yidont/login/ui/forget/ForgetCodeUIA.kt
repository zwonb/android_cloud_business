package com.yidont.login.ui.forget

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.yidont.lib.util.KeyValue
import com.yidont.login.ui.InputCodeUIA

/**
 * 找回密码-验证码
 *
 * @author zwonb
 * @date 2019-12-18
 */
class ForgetCodeUIA : InputCodeUIA() {

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.style = "findPassword"
        super.initView(savedInstanceState)

        viewModel.checkCode.observe(this, Observer {
            val intent = Intent(this, ForgetPwdUIA::class.java)
            intent.putExtra(KeyValue.PHONE, viewModel.phone)
            startActivity(intent)
            finish()
        })
    }
}