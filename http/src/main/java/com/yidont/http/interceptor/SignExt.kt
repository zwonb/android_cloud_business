package com.yidont.http.interceptor

import android.util.Base64
import com.yidont.http.upload.TextBody
import com.yidont.lib.extension.hmacSHA256
import com.yidont.lib.extension.md5
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.MultipartBody
import java.net.URLEncoder

/**
 * 签名校验
 *
 * @author zwonb
 * @date 2019-12-13
 */

fun sign(url: HttpUrl): String {
    val map = mutableMapOf<String, String>()
    for (key in url.queryParameterNames()) {
        url.queryParameter(key)?.let {
            map[key] = it
        }
    }
    return signDailyTelecom(map)
}

fun sign(formBody: FormBody): String {
    val map = mutableMapOf<String, String>()
    for (i in 0 until formBody.size()) {
        map[formBody.name(i)] = formBody.value(i)
    }
    return signDailyTelecom(map)
}

fun sign(parts: List<MultipartBody.Part>): String {
    val map = mutableMapOf<String, String>()
    for (part in parts) {
        val body = part.body()
        if (body is TextBody) {
            map[body.key] = body.value
        }
    }
    return signDailyTelecom(map)
}

private fun signTTOA(map: Map<String, String>): String {
    val sortSign = sortSign(map)
    val shA256 = URLEncoder.encode(sortSign, "UTF-8")
        .toByteArray()
        .hmacSHA256()
    return String(Base64.encode(shA256, Base64.NO_WRAP))
}

private fun signDailyTelecom(map: Map<String, String>): String {
    val sortSign = sortSign(map)
    val one = URLEncoder.encode(sortSign, "UTF-8") + HeadersInterceptor.CLUEID_KEY
    val two = one.md5() + HeadersInterceptor.CLUEID_KEY
    val three = two.md5()
    val encode = URLEncoder.encode(three, "UTF-8")
    return String(Base64.encode(encode.toByteArray(), Base64.NO_WRAP))
}

private fun sortSign(map: Map<String, String>): String {
    if (map.isEmpty()) {
        return ""
    }
    val result = StringBuilder()
    val sortedMap = map.toSortedMap()
    val firstKey = sortedMap.firstKey()
    for ((key, value) in sortedMap) {
        if (key != firstKey) {
            result.append("&")
        }
        result.append(key)
            .append("=")
            .append(value)
    }
    return result.toString()
}