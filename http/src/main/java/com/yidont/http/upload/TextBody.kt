package com.yidont.http.upload

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink

/**
 * 文本类型 text/plain
 *
 * @author zwonb
 * @date 2019-12-14
 */
class TextBody(val key: String, val value: String, private val listener: UploadListener? = null) :
    RequestBody() {

    init {
        listener?.apply {
            total += contentLength()
        }
    }

    override fun contentType(): MediaType? {
        return MediaType.parse("text/plain; charset=UTF-8")
    }

    override fun contentLength(): Long {
        return try {
            value.toByteArray().size.toLong()
        } catch (e: Exception) {
            super.contentLength()
        }
    }

    override fun writeTo(sink: BufferedSink) {
        value.byteInputStream().use {
            val buffer = ByteArray(1024 * 8)
            var read = it.read(buffer)
            while (read >= 0) {
                sink.write(buffer, 0, read)
                listener?.updated(read.toLong())
                read = it.read(buffer)
            }
        }
    }
}

fun MutableList<MultipartBody.Part>.addText(
    key: String, value: String, listener: UploadListener? = null
) = this.add(textPart(key, value, listener))

fun textPart(key: String, value: String, listener: UploadListener? = null) =
    MultipartBody.Part.createFormData(key, null, TextBody(key, value, listener))