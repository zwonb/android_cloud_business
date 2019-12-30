package com.yidont.lib.banner

import android.content.Context
import android.widget.ImageView
import com.yidont.lib.img.ImgLoader
import com.yidont.lib.img.ImgLoader.getOptions
import com.yidont.lib.img.ImgLoader.load
import com.youth.banner.loader.ImageLoader

/**
 * banner 图片加载
 */
class BannerImgLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        if (path.toString().contains("http")) {
            load(context, path as String, imageView, getOptions(ImgLoader.TYPE_BANNER))
        } else {
            imageView.setImageResource(path as Int)
        }
    }
}