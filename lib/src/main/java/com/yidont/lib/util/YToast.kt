package com.yidont.lib.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes
import com.yidont.lib.R
import com.yidont.lib.provider.appContext

/**
 * toast
 *
 * @author zwonb
 * @date 2019-12-06
 */
object YToast {

    private var sToast: Toast? = null

    fun show(text: CharSequence?) {
        initToast()?.apply {
            setText(text)
            show()
        }
    }

    fun show(@StringRes resId: Int) {
        initToast()?.apply {
            setText(resId)
            show()
        }
    }

    @SuppressLint("InflateParams")
    private fun initToast(): Toast? {
        if (sToast != null) {
            sToast?.cancel()
            sToast = null
        }
        sToast = Toast(appContext)
        val view =
            LayoutInflater.from(appContext).inflate(R.layout.toast_layout, null)
        sToast?.view = view
        sToast?.duration = Toast.LENGTH_SHORT
        return sToast
    }
}

fun showToast(text: CharSequence?) = YToast.show(text)
fun showToast(@StringRes resId: Int) = YToast.show(resId)