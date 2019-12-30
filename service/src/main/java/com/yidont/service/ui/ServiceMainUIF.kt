package com.yidont.service.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.yidont.lib.base.BaseUIF
import com.yidont.service.R
import com.yidont.service.data.ServiceRepository
import com.yidont.service.data.model.ServiceCardBean
import com.yidont.service.ui.holder.ServiceBannerBinder
import com.yidont.service.ui.holder.ServiceCardBinder
import com.yidont.service.ui.holder.ServiceTextBinder
import com.yidont.service.ui.holder.ServiceTutorialBinder
import kotlinx.android.synthetic.main.recycler_view.*

/**
 * 主页-服务
 *
 * @author zwonb
 * @date 2019-12-23
 */
class ServiceMainUIF : BaseUIF(R.layout.recycler_view) {

    private val adapter = MultiTypeAdapter()

    private val viewModel by viewModels<ServiceMainVM> {
        ServiceModelFactory(ServiceRepository())
    }

    override fun initView() {
        initRV()
    }

    private fun initRV() {
        val manager = GridLayoutManager(context, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val item = viewModel.serviceData[position]
                return if (item is ServiceCardBean) 1 else manager.spanCount
            }
        }
        recycler_view.layoutManager = manager
        adapter.register(ServiceBannerBinder())
        adapter.register(ServiceTextBinder())
        adapter.register(ServiceCardBinder())
        adapter.register(ServiceTutorialBinder())
        adapter.items = viewModel.serviceData
        recycler_view.adapter = adapter
    }

}