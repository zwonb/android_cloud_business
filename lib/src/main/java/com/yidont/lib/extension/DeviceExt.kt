package com.yidont.lib.extension

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import java.util.*


/**
 * 设备信息
 *
 * @author zwonb
 * @date 2019/10/15
 */
fun getDevInfo(): String {
    return "35" + Build.BOARD.length % 10 +
            Build.BRAND.length % 10 +
            Build.SUPPORTED_ABIS[0].length % 10 +
            Build.DEVICE.length % 10 +
            Build.DISPLAY.length % 10 +
            Build.HOST.length % 10 +
            Build.ID.length % 10 +
            Build.MANUFACTURER.length % 10 +
            Build.MODEL.length % 10 +
            Build.PRODUCT.length % 10 +
            Build.TAGS.length % 10 +
            Build.TYPE.length % 10 +
            Build.USER.length % 10
}

@SuppressLint("HardwareIds")
fun Context.getAndroidId(): String {
    val id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    return if (id == null || id == "9774d56d682e549c") {
        // 一些手机可能会返回null，或者固定值9774d56d682e549c
        try {
            val devId = getDevInfo().hashCode().toLong()
            UUID(devId, devId shl 32).toString()
        } catch (e: Exception) {
//            Log.e("zwonb", "发生错误getAndroidId: ${e.message}")
            UUID.randomUUID().toString()
        }
    } else {
        id
    }
}