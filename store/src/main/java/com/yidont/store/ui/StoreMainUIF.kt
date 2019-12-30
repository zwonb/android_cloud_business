package com.yidont.store.ui

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.yidont.lib.base.BaseUIF
import com.yidont.store.R
import com.yidont.store.data.StoreRepository
import com.yidont.store.ui.holder.StoreHeadBinder
import com.yidont.store.ui.holder.StoreTextBinder
import kotlinx.android.synthetic.main.recycler_view.*

/**
 * 主页-门店
 *
 * @author zwonb
 * @date 2019-12-24
 */
class StoreMainUIF : BaseUIF(R.layout.recycler_view) {

    private val viewModel by viewModels<StoreMainVM> {
        StoreModelFactory(StoreRepository())
    }
    private val adapter = MultiTypeAdapter()

    override fun initView() {
        context?.let {
            recycler_view.setBackgroundColor(ContextCompat.getColor(it, R.color.line_f0))
        }
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter.register(StoreHeadBinder())
        adapter.register(StoreTextBinder())
        adapter.items = viewModel.storeData
        recycler_view.adapter = adapter
    }
}