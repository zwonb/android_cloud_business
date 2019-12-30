package com.yidont.lib.util

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.yidont.lib.provider.appContext

/**
 * @author zwonb
 * @date 2019-12-17
 */
fun getVersionCode() = try {
    val packageInfo = appContext.packageManager.getPackageInfo(
        appContext.packageName,
        PackageManager.GET_ACTIVITIES
    )
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        Log.e("zwonb", "getVersionCode: ${packageInfo.longVersionCode}")
        packageInfo.longVersionCode.toString()
    } else {
        packageInfo.versionCode.toString()
    }
} catch (e: Throwable) {
    "0"
}