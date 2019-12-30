package com.yidont.lib.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.yidont.lib.R
import com.zwonb.headbar.HeadBar
import kotlinx.android.synthetic.main.base_uia_head_bar.*

/**
 * activity 带头部基类
 *
 * @author zwonb
 * @date 2019-12-17
 */
abstract class BaseHeadBarUIA(@LayoutRes val contentLayoutId: Int = 0) :
    BaseUIA(R.layout.base_uia_head_bar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initHeadBar()
        if (contentLayoutId != 0) {
            layoutInflater.inflate(contentLayoutId, content_layout, true)
        }
    }

    private fun initHeadBar() {
        head_bar?.apply {
            backClick { finish() }
            headBar(this)
        }
    }

    abstract fun headBar(headBar: HeadBar)


    val headBarLoadingObserver = Observer<Boolean> {
        headBarLoadStatus(it)
    }

    fun headBarLoadStatus(it: Boolean) = if (it) {
        head_bar.startLoading()
    } else {
        head_bar.stopLoading()
    }
}