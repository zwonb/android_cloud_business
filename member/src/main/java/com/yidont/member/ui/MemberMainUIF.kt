package com.yidont.member.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.yidont.lib.base.BaseUIF
import com.yidont.lib.util.showToast
import com.yidont.member.R
import com.yidont.member.data.repository.MemberRepository
import com.yidont.member.ui.holder.MemberBinder
import com.yidont.member.ui.window.MemberAttrPop
import com.yidont.member.ui.window.MemberFilterPop
import kotlinx.android.synthetic.main.uif_member_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 主页-会员
 *
 * @author zwonb
 * @date 2019-12-20
 */
class MemberMainUIF : BaseUIF(R.layout.uif_member_main), SwipeRecyclerView.LoadMoreListener {

    private val viewModel by viewModels<MemberMainViewModel> {
        MemberModelFactory(MemberRepository())
    }
    private var filterPop: MemberFilterPop? = null
    private var filterAttrPop: MemberAttrPop? = null
    private val adapter = MultiTypeAdapter()

    override fun initView() {
        initFilter()

        member_attr.setOnClickListener {
            showFilterAttrPop()
        }

        viewModel.gradeLive.observe(this, Observer {
            member_grade.text = it.title
//            viewModel.refresh()
        })
        viewModel.labelLive.observe(this, Observer {
            member_label.text = it.title
//            viewModel.refresh()
        })

        initMember()
    }

    private fun initFilter() {
        viewModel.requestGrade()
        viewModel.requestLabel()

        viewModel.gradeData.observe(this, Observer {
            member_grade.setOnClickListener {
                viewModel.filterType = 0
                showFilterPop()
            }
        })
        viewModel.labelData.observe(this, Observer {
            member_label.setOnClickListener {
                viewModel.filterType = 1
                showFilterPop()
            }
        })
    }

    private fun initMember() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter.register(MemberBinder({ showToast(adapter.itemCount.toString()) }))
        adapter.items = viewModel.memberData

        recycler_view.useDefaultLoadMore()
        recycler_view.setLoadMoreListener(this)

        recycler_view.adapter = adapter

        recycler_view.loadMoreFinish(false, true)

//        recycler_view.addOnScrollListener(object :
//            RVScrollListener(recycler_view.layoutManager as LinearLayoutManager) {
//            override fun onLoadMore() {
//                viewModel.isLoading = true
//                viewModel.viewModelScope.launch {
//                    delay(1000)
//                    val start = adapter.itemCount
//                    adapter.items.toMutableList().apply {
//                        addAll(start, viewModel.memberData)
//                        adapter.items = this
//                        adapter.notifyItemRangeInserted(start, viewModel.memberData.size)
//                    }
//                    viewModel.isLoading = false
//                }
//            }
//
//            override fun isDataLoading(): Boolean {
//                return viewModel.isLoading
//            }
//
//            override fun isNoMoreData(): Boolean {
//                return adapter.items.size > 20
//            }
//        })
    }

    /**
     * 会员等级、会员标签
     */
    private fun showFilterPop() {
        if (filterPop == null) {
            filterPop = MemberFilterPop(context!!, viewModel)
        } else {
            filterPop!!.updateData()
        }
        filterPop!!.showAsDropDown(head_background)
    }

    /**
     * 会员属性
     */
    private fun showFilterAttrPop() {
        if (filterAttrPop == null) {
            filterAttrPop = MemberAttrPop(context!!, viewModel)
        }
        filterAttrPop!!.showAsDropDown(head_background)
    }

    override fun onLoadMore() {
        viewModel.viewModelScope.launch {
            delay(1000)
            val start = adapter.itemCount
            adapter.items.toMutableList().apply {
                addAll(start, viewModel.memberData)
                adapter.items = this
                adapter.notifyItemRangeInserted(start, viewModel.memberData.size)

                recycler_view.loadMoreFinish(false, true)
            }
        }
    }


}