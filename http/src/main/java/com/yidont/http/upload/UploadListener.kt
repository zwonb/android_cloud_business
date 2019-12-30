package com.yidont.http.upload

import java.util.concurrent.atomic.AtomicLong

/**
 * 请求上传进度监听
 *
 * @author zwonb
 * @date 2019-12-14
 */
open class UploadListener {

    var total = 0L
    private var uploaded = AtomicLong()
    private var time = System.currentTimeMillis()

    fun updated(load: Long) {
        val percent = uploaded.addAndGet(load) * 100.toDouble() / total
        if (percent == 100.0) {
            percent(percent)
        } else {
            if (System.currentTimeMillis() - 120 >= time) {
                percent(percent)
                time = System.currentTimeMillis()
            }
        }
    }

    open fun percent(percent: Double) {

    }
}