package com.yidont.lib.extension

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * 安全相关拓展类
 *
 * @author zwonb
 * @date 2019/7/18
 */

/* 铁通 */
private const val DES3_KEY = "Tietong.yidont.comZhou!234567"
private const val DES3_IV = "VaQTieto"
private const val HMACSHA256_KEY = "api.tietong.comzhen!@#%^&"

/* 天天电信 */
//private const val DES3_KEY = "Italy.yidont.comZhoushouby!23456"
//private const val DES3_IV = "VaQItaly"

private const val AES_KEY = "cswifi.yidont.comZhoushouby!@#$%"
private const val AES_IV = "p0dQwifi12345678"

/* AES 加密 */
fun String?.aesEncrypt(): String {
    return if (isNullOrBlank()) {
        ""
    } else {
        aes(true, this)
    }
}

/* AES 解密 */
fun String?.aesDecrypt(): String {
    return if (isNullOrBlank()) {
        ""
    } else {
        aes(false, this)
    }
}

/* MD5 编码 */
fun String?.md5(): String {
    if (this.isNullOrBlank()) {
        return ""
    }
    return try {
        // SHA-1、SHA-256 通用
        val md5 = MessageDigest.getInstance("MD5")
        md5.digest(this.toByteArray()).toHex()
    } catch (e: Exception) {
        ""
    }
}

fun ByteArray?.hmacSHA256(): ByteArray = try {
    val algorithm = "HmacSHA256"
    Mac.getInstance(algorithm).run {
        init(SecretKeySpec(HMACSHA256_KEY.toByteArray(), algorithm))
        doFinal(this@hmacSHA256)
    }
} catch (e: Exception) {
    byteArrayOf()
}

/* 3des 加密 */
fun String?.des3Encrypt(): String {
    return des3(true, this)
}

/* 3des 解密 */
fun String?.des3Decrypt(): String {
    return des3(false, this)
}

private fun aes(isEncrypt: Boolean, value: String?): String {
    if (value.isNullOrBlank()) {
        return ""
    }
    try {
        val secretKey = SecretKeySpec(AES_KEY.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivParameter = IvParameterSpec(AES_IV.toByteArray())
        cipher.init(
            if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE,
            secretKey,
            ivParameter
        )
        val decode = if (isEncrypt) {
            val bytes = cipher.doFinal(value.toByteArray())
            Base64.encode(bytes, Base64.NO_WRAP)
        } else {
            val decode = Base64.decode(value, Base64.NO_WRAP)
            cipher.doFinal(decode)
        }
        return String(decode)
    } catch (e: Exception) {
        return ""
    }
}

private fun des3(isEncrypt: Boolean, value: String?): String {
    if (value.isNullOrBlank()) {
        return ""
    }
    try {
        val secretKey = SecretKeyFactory.getInstance("desede").run {
            generateSecret(DESedeKeySpec(DES3_KEY.toByteArray()))
        }
        val cipher = Cipher.getInstance("desede/CBC/PKCS5Padding").apply {
            init(
                if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE,
                secretKey,
                IvParameterSpec(DES3_IV.toByteArray())
            )
        }
        val decode = if (isEncrypt) {
            val bytes = cipher.doFinal(value.toByteArray())
            Base64.encode(bytes, Base64.NO_WRAP)
        } else {
            val decode = Base64.decode(value, Base64.NO_WRAP)
            cipher.doFinal(decode)
        }
        return String(decode)
    } catch (e: Exception) {
        return ""
    }
}

/**
 * ByteArray 转成十六进制 String
 */
val HEX_CHARS = "0123456789abcdef".toCharArray()

fun ByteArray.toHex(): String {
    val result = StringBuilder(size * 2)
    forEach { b ->
        val i = b.toInt()
        result.append(HEX_CHARS[i shr 4 and 0xF])
        result.append(HEX_CHARS[i and 0xF])
    }
    return result.toString()
}
