package com.yidont.login.ui.forget

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.yidont.lib.util.KeyValue
import com.yidont.login.ui.InputPhoneUIA
import com.zwonb.headbar.HeadBar

/**
 * 找回密码-输入手机号码
 *
 * @author zwonb
 * @date 2019-12-18
 */
class ForgetPhoneUIA : InputPhoneUIA() {

    override fun headBar(headBar: HeadBar) {
        headBar.setTitle("找回密码")
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.style = "findPassword"
        super.initView(savedInstanceState)

        viewModel.getCode.observe(this, Observer {
            val intent = Intent(this, ForgetCodeUIA::class.java)
            intent.putExtra(KeyValue.PHONE, viewModel.phone)
            startActivity(intent)
            finish()
        })
    }
}