package com.yidont.login.ui.register

import android.os.Bundle
import androidx.lifecycle.Observer
import com.yidont.login.ui.InputPwdUIA
import com.zwonb.headbar.HeadBar

/**
 * 注册-输入密码
 *
 * @author zwonb
 * @date 2019-12-18
 */
class RegisterPwdUIA : InputPwdUIA() {

    override fun headBar(headBar: HeadBar) {
        headBar.setTitle("输入密码完成注册")
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.style = "userRegister"
        super.initView(savedInstanceState)

        viewModel.checkPwd.observe(this, Observer {
//            val intent = Intent(this, LoginUIA::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(intent)
            finish()
        })
    }

}