package com.yidont.cloud.business.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.yidont.cloud.business.R
import com.yidont.cloud.business.data.model.HomeBean
import com.yidont.cloud.business.ui.home.holder.SortAddedBinder
import com.yidont.cloud.business.ui.home.holder.SortNoAddBinder
import com.yidont.lib.base.BaseUIA
import kotlinx.android.synthetic.main.uia_function_sort.*

/**
 * 首页常用功能排序
 *
 * @author zwonb
 * @date 2019-12-19
 */
class FunctionSortUIA : BaseUIA(R.layout.uia_function_sort) {

    private val adapter1 = MultiTypeAdapter()
    private val adapter2 = MultiTypeAdapter()

    override fun initView(savedInstanceState: Bundle?) {
        close.setOnClickListener { finish() }
        recyclerView1()
        recyclerView2()
    }

    private fun recyclerView1() {
        recycler_view1.layoutManager = GridLayoutManager(this, 3)
        adapter1.register(SortAddedBinder(adapter2))
        adapter1.items = getList1()
        recycler_view1.adapter = adapter1
    }

    private fun recyclerView2() {
        recycler_view2.layoutManager = GridLayoutManager(this, 3)
        adapter2.register(SortNoAddBinder(adapter1))
        adapter2.items = getList2()
        recycler_view2.adapter = adapter2
    }

    private fun getList1(): List<HomeBean> {
        val list = mutableListOf<HomeBean>()
        for (i in 0..5) {
            list.add(HomeBean(0, ""))
        }
        return list
    }

    private fun getList2(): List<HomeBean> {
        val list = mutableListOf<HomeBean>()
        for (i in 0..3) {
            list.add(HomeBean(0, ""))
        }
        return list
    }

}