package com.yidont.login.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.yidont.lib.util.KeyValue
import com.yidont.login.ui.InputPhoneUIA
import com.zwonb.headbar.HeadBar

/**
 * 注册-输入手机号码
 *
 * @author zwonb
 * @date 2019-12-18
 */
class RegisterPhoneUIA : InputPhoneUIA() {

    override fun headBar(headBar: HeadBar) {
        headBar.setTitle("用户注册")
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.style = "userRegister"
        super.initView(savedInstanceState)

        viewModel.getCode.observe(this, Observer {
            val intent = Intent(this, RegisterCodeUIA::class.java)
            intent.putExtra(KeyValue.PHONE, viewModel.phone)
            startActivity(intent)
            finish()
        })

    }
}