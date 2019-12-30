package com.yidont.login.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.yidont.lib.base.BaseHeadBarUIA
import com.yidont.lib.util.KeyValue
import com.yidont.lib.util.showToast
import com.yidont.login.R
import com.yidont.login.data.repository.LoginRepository
import com.zwonb.headbar.HeadBar
import kotlinx.android.synthetic.main.fragment_input_code.*

/**
 * @author zwonb
 * @date 2019-12-17
 */
abstract class InputCodeUIA : BaseHeadBarUIA(R.layout.fragment_input_code) {

    val viewModel by viewModels<LoginViewModel> { LoginModelFactory(LoginRepository()) }
    private val codeDown = CodeCountDown()

    private val phone by lazy(LazyThreadSafetyMode.NONE) {
        intent.getStringExtra(KeyValue.PHONE) ?: ""
    }

    override fun headBar(headBar: HeadBar) {
        headBar.setTitle("输入验证码")
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.phone = phone
        initTip()
        codeDownText()

        code_execute.setOnClickListener {
            viewModel.checkCode(code_et.text.toString())
        }

        viewModel.getCode.observe(this, Observer {
            showToast("发送成功，请注意查收")
            codeDown.start()
        })
        viewModel.isLoading.observe(this, Observer {
            if (code_again.isEnabled) {
                code_again.isEnabled = !it
            }
            code_execute.isEnabled = !it
            headBarLoadStatus(it)
        })
    }

    private fun initTip() {
        if (phone.isEmpty()) {
            return
        }
        val spannable = SpannableStringBuilder("我们已向您的手机号码 +86-")
            .append(phone)
        val themeColor = ContextCompat.getColor(this, R.color.theme)
        spannable.setSpan(
            ForegroundColorSpan(themeColor),
            11,
            26,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.append("发送一条短信验证码，请注意查收！")
        code_des.text = spannable
    }

    private fun codeDownText() {
        codeDown.start()
        viewModel.codeCountDown.observe(this, Observer {
            if (it == 0L) {
                code_again.isEnabled = true
                code_again.text = "再次获取"
                code_again.setOnClickListener {
                    viewModel.getCode(phone)
                }
            } else {
                if (code_again.isEnabled) {
                    code_again.isEnabled = false
                    code_again.setOnClickListener(null)
                }
                code_again.text = "$it 秒"
            }
        })
    }

    inner class CodeCountDown : CountDownTimer(61000, 1000) {
        override fun onTick(millis: Long) {
            val sec = millis / 1000
            viewModel.codeCountDown.value = sec - 1
        }

        override fun onFinish() {
            viewModel.codeCountDown.value = 0
        }
    }

    override fun onDestroy() {
        codeDown.cancel()
        super.onDestroy()
    }

}