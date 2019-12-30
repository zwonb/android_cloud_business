package com.yidont.service.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.MultiTypeAdapter
import com.yidont.service.R
import com.yidont.service.data.model.ServiceTutorialBean
import com.yidont.service.data.model.TutorialBean

/**
 * 服务-官网教程
 *
 * @author zwonb
 * @date 2019-12-23
 */
class ServiceTutorialBinder() :
    ItemViewBinder<ServiceTutorialBean, ServiceTutorialBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: ServiceTutorialBean) {
        holder.setData(item.img)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.recycler_view, parent, false)
        )
    }

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val adapter = MultiTypeAdapter()
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view)

        init {
            val layoutManager = LinearLayoutManager(itemView.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView.layoutManager = layoutManager
            adapter.register(TutorialBinder())
            recyclerView.adapter = adapter
        }

        fun setData(data: List<TutorialBean>) {
            adapter.items = data
            adapter.notifyDataSetChanged()
        }
    }
}

class TutorialBinder() : ItemViewBinder<TutorialBean, TutorialBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: TutorialBean) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_service_tutorial, parent, false)
        )
    }

    class Holder(item: View) : RecyclerView.ViewHolder(item) {

    }
}