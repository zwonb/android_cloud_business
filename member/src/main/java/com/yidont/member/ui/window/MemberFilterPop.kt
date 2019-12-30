package com.yidont.member.ui.window

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.yidont.member.R
import com.yidont.member.data.model.MemberFilterBean
import com.yidont.member.ui.MemberMainViewModel
import com.yidont.member.ui.holder.MemberFilterBinder

/**
 * 会员筛选弹窗
 *
 * @author zwonb
 * @date 2019-12-20
 */
class MemberFilterPop(private val context: Context, private val viewModel: MemberMainViewModel) :
    PopupWindow(context) {

    private val adapter = MultiTypeAdapter()

    init {
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isFocusable = true
        isOutsideTouchable = true
        setBackgroundDrawable(ColorDrawable(Color.WHITE))
        setOnDismissListener { resetBG(context) }

        initView(if (viewModel.filterType == 0) viewModel.gradeData.value else viewModel.labelData.value)
    }

    private fun initView(data: List<MemberFilterBean>?) {
        if (data.isNullOrEmpty()) {
            return
        }
        contentView = LayoutInflater.from(context).inflate(R.layout.recycler_view, null).apply {
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(context)

            adapter.register(MemberFilterBinder {
                selectFilter(it)
            })
            adapter.items = data
            recyclerView.adapter = adapter
        }
    }

    /**
     * 点击筛选
     */
    private fun selectFilter(it: MemberFilterBean) {
        if (viewModel.filterType == 0) {
            // 会员等级
            for (filter in viewModel.gradeData.value!!) {
                if (filter.status) {
                    filter.status = false
                }
            }
            it.status = true
            viewModel.gradeLive.value = it
        } else {
            // 会员标签
            for (filter in viewModel.labelData.value!!) {
                if (filter.status) {
                    filter.status = false
                }
            }
            it.status = true
            viewModel.labelLive.value = it
        }
        this@MemberFilterPop.dismiss()
    }

    fun updateData() {
        adapter.items = if (viewModel.filterType == 0) viewModel.gradeData.value!! else viewModel.labelData.value!!
        adapter.notifyDataSetChanged()
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        setGaryBG(context)
        super.showAsDropDown(anchor, xoff, yoff, gravity)
    }

    private fun setGaryBG(context: Context) {
        if (context is Activity) {
            val window = context.window
            val lp = window.attributes
            lp.alpha = 0.4f
            window.attributes = lp
        }
    }

    private fun resetBG(context: Context) {
        if (context is Activity) {
            val window = context.window
            val lp = window.attributes
            lp.alpha = 1f
            window.attributes = lp
        }
    }

}