package com.yidont.service.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.google.android.material.chip.Chip
import com.yidont.service.R
import com.yidont.service.data.model.ServiceTextBean

/**
 * 服务-文本(我的服务)
 *
 * @author zwonb
 * @date 2019-12-23
 */
class ServiceTextBinder() : ItemViewBinder<ServiceTextBean, ServiceTextBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: ServiceTextBean) {
        holder.text.text = item.text
        if (item.text == "服务市场") {
            holder.myService.visibility = View.VISIBLE
        } else {
            holder.myService.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_service_text, parent, false)
        )
    }

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        val text = itemView.findViewById<TextView>(R.id.item_text)
        val myService = itemView.findViewById<Chip>(R.id.my_service)
    }
}