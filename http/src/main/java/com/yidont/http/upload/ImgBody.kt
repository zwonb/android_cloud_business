package com.yidont.http.upload

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.util.concurrent.atomic.AtomicLong

/**
 * 图片类型 image/jpg
 *
 * @author zwonb
 * @date 2019/9/30
 */
class ImgBody(val file: File, private val listener: UploadListener? = null) : RequestBody() {

    init {
        listener?.apply {
            total += contentLength()
        }
    }

    override fun contentType(): MediaType? {
        return MediaType.parse("image/jpg")
    }

    override fun contentLength(): Long {
        return file.length()
    }

    override fun writeTo(sink: BufferedSink) {
        file.inputStream().use {
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

fun MutableList<MultipartBody.Part>.addImg(
    name: String,
    img: File,
    listener: UploadListener? = null
) = this.add(MultipartBody.Part.createFormData(name, img.name, ImgBody(img, listener)))
