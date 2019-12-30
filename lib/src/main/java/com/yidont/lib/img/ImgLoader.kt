package com.yidont.lib.img

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.yidont.lib.R
import java.io.File

/**
 * 图片加载工具类
 *
 * @author zwonb
 * @date 2018/7/31
 */

object ImgLoader {

    const val TYPE_NORMAL = 0
    const val TYPE_ROUND = 1
    const val TYPE_BANNER = 2
    const val TYPE_PHOTO = 3
    const val TYPE_ADD_IMG = 4

    fun load(context: Any, url: String?, view: ImageView) {
        load(context, url, view, getOptions())
    }

    fun load(context: Any, file: File?, view: ImageView) {
        load(context, file, view, getOptions())
    }

    fun load(context: Any, url: String?, view: ImageView, requestOptions: RequestOptions) {
        getGlideRequest(context).load(url).thumbnail(0.2f).apply(requestOptions).into(view)
    }

    fun load(context: Any, file: File?, view: ImageView, requestOptions: RequestOptions) {
        getGlideRequest(context).load(file).thumbnail(0.2f).apply(requestOptions).into(view)
    }

    /**
     * 添加图片
     */
    fun loadAddImg(context: Any, url: String?, view: ImageView) {
        load(context, url, view, getOptions(TYPE_ADD_IMG))
    }

    /**
     * 添加图片
     */
    fun loadAddImg(context: Any, file: File?, view: ImageView) {
        load(context, file, view, getOptions(TYPE_ADD_IMG))
    }

    /**
     * 圆形头像
     */
    fun loadRoundHead(context: Any, url: String?, view: ImageView) {
        load(context, url, view, getOptions(TYPE_PHOTO))
    }

    /**
     * 圆形头像
     */
    fun loadRoundHead(context: Any, file: File?, view: ImageView) {
        load(context, file, view, getOptions(TYPE_PHOTO))
    }

    /**
     * 圆形 url 图片
     */
    fun loadRound(context: Any, url: String?, view: ImageView) {
        load(context, url, view, getOptions(TYPE_ROUND))
    }

    /**
     * 圆形 file 图片
     */
    fun loadRound(context: Any, file: File?, view: ImageView) {
        load(context, file, view, getOptions(TYPE_ROUND))
    }

    /**
     * 加载的选项
     */
    fun getOptions(type: Int = TYPE_NORMAL): RequestOptions {
        val options = RequestOptions()
        when (type) {
            TYPE_NORMAL -> options
                    .placeholder(R.mipmap.pic_normal_placeholder)
                    .error(R.mipmap.pic_normal_placeholder)
            TYPE_ROUND -> options
                    .placeholder(R.mipmap.pic_normal_placeholder)
                    .error(R.mipmap.pic_normal_placeholder)
                    .circleCrop()
            TYPE_BANNER -> options
                    .placeholder(R.mipmap.pic_banner_placeholder)
                    .error(R.mipmap.pic_banner_placeholder)
                    .centerCrop()
                    .skipMemoryCache(true)
            TYPE_PHOTO -> options
                    .placeholder(R.mipmap.ic_photo)
                    .error(R.mipmap.ic_photo)
                    .circleCrop()
            TYPE_ADD_IMG -> options
                    .placeholder(R.mipmap.ic_add_img)
                    .error(R.mipmap.ic_add_img)
        }
        return options
    }

    private fun getGlideRequest(context: Any): RequestManager {
        return when (context) {
            is Context -> Glide.with(context)
            is FragmentActivity -> Glide.with(context)
            is Fragment -> Glide.with(context)
            else -> throw IllegalArgumentException("请传入正确的 context")
        }
    }
}
