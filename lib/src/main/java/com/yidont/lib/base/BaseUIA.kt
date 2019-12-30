package com.yidont.lib.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * activity 基类
 *
 * @author zwonb
 * @date 2019-12-04
 */
abstract class BaseUIA(@LayoutRes contentLayoutId: Int = 0) : AppCompatActivity(contentLayoutId) {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    fun startAct(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}