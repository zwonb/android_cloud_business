package com.yidont.service.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.yidont.lib.banner.BannerImgLoader
import com.yidont.service.R
import com.yidont.service.data.model.ServiceBannerBean
import com.youth.banner.Banner

/**
 * 服务-banner
 *
 * @author zwonb
 * @date 2019-12-23
 */
class ServiceBannerBinder() : ItemViewBinder<ServiceBannerBean, ServiceBannerBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: ServiceBannerBean) {
        holder.setBanner(item)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_service_banner, parent, false)
        )
    }

    override fun onViewAttachedToWindow(holder: Holder) {
        super.onViewAttachedToWindow(holder)
        holder.banner.startAutoPlay()
    }

    override fun onViewDetachedFromWindow(holder: Holder) {
        super.onViewDetachedFromWindow(holder)
        holder.banner.stopAutoPlay()
    }

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        val banner: Banner = itemView.findViewById(R.id.item_banner)

        fun setBanner(bean: ServiceBannerBean) {
            banner.setImageLoader(BannerImgLoader())
            banner.setImages(bean.img)
            banner.start()
        }

    }
}