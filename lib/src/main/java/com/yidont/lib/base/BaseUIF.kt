package com.yidont.lib.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * fragment 基类
 *
 * @author zwonb
 * @date 2019-12-16
 */
abstract class BaseUIF(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()

}