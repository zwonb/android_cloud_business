package com.yidont.lib.util

import android.content.Context
import androidx.core.content.edit
import com.yidont.lib.provider.appContext

/**
 * @author zwonb
 * @date 2019-12-25
 */
object SPUtil {
    private const val NAME_CONFIG = "config"

    fun getPreferences(name: String = NAME_CONFIG) =
        appContext.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun getUid() = getPreferences().getString(KeyValue.UID, "") ?: ""

    fun getPhone() = getPreferences().getString(KeyValue.PHONE, "") ?: ""

    fun putUid(uid: String) = getPreferences().edit {
        putString(KeyValue.UID, uid)
    }

    fun putPhone(phone: String) = getPreferences().edit {
        putString(KeyValue.PHONE, phone)
    }
}