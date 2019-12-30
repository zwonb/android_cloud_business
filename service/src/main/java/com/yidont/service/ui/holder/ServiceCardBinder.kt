package com.yidont.service.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.yidont.service.R
import com.yidont.service.data.model.ServiceCardBean

/**
 * 服务-服务市场
 *
 * @author zwonb
 * @date 2019-12-23
 */
class ServiceCardBinder() : ItemViewBinder<ServiceCardBean, ServiceCardBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: ServiceCardBean) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_service_card, parent, false),
            this
        )
    }

    class Holder(item: View, binder: ServiceCardBinder) : RecyclerView.ViewHolder(item) {

//        init {
//            item.setOnClickListener {
//                binder.click(binder.adapter.items[adapterPosition] as ServiceCardBean)
//            }
//        }
    }
}