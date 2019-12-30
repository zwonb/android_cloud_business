package com.yidont.cloud.business.ui.home

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.yidont.cloud.business.R
import com.yidont.cloud.business.data.model.HomeBean
import com.yidont.cloud.business.ui.home.holder.HomeHolder
import com.yidont.lib.base.BaseUIF
import kotlinx.android.synthetic.main.uif_home.*

/**
 * 首页
 *
 * @author zwonb
 * @date 2019-12-18
 */
class HomeUIF : BaseUIF(R.layout.uif_home) {

    private val adapter = MultiTypeAdapter()

    override fun initView() {
        more.setOnClickListener {
            startActivity(Intent(context, FunctionSortUIA::class.java))
        }

        recycler_view.layoutManager = GridLayoutManager(context, 3)
        adapter.register(HomeHolder())
        adapter.items = getList()
        recycler_view.adapter = adapter
    }

    private fun getList(): List<HomeBean> {
        val list = mutableListOf<HomeBean>()
        for (i in 0..5) {
            list.add(HomeBean(0, ""))
        }
        return list
    }

}