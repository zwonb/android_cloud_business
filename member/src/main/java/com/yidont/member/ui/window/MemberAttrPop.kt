package com.yidont.member.ui.window

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yidont.lib.util.showToast
import com.yidont.member.R
import com.yidont.member.data.model.MemberAttrBean
import com.yidont.member.ui.MemberMainViewModel

/**
 * 会员属性筛选弹窗
 *
 * @author zwonb
 * @date 2019-12-20
 */
class MemberAttrPop(private val context: Context, private val viewModel: MemberMainViewModel) :
    PopupWindow(context) {

    init {
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isFocusable = true
        isOutsideTouchable = true
        setBackgroundDrawable(ColorDrawable(Color.WHITE))
        setOnDismissListener { resetBG(context) }

        initView(viewModel.attrData)
    }

    private fun initView(data: List<List<MemberAttrBean>>) {
        contentView = LayoutInflater.from(context).inflate(R.layout.pop_member_attr, null).apply {
            addLayout(data, this)
            findViewById<TextView>(R.id.pop_search).setOnClickListener {
                filterSearch()
            }
        }
    }

    private fun filterSearch() {
        dismiss()
        showToast(viewModel.attrCheck.toString())
    }

    private fun addLayout(data: List<List<MemberAttrBean>>, view: View) {
        for (i in data.indices) {
            when (i) {
                0 -> {
                    val group = view.findViewById<ChipGroup>(R.id.group1)
                    groupCheck(group, i)
                    addChip(data[i], group)
                }
                1 -> {
                    val group = view.findViewById<ChipGroup>(R.id.group2)
                    groupCheck(group, i)
                    addChip(data[i], group)
                }
                2 -> {
                    val group = view.findViewById<ChipGroup>(R.id.group3)
                    groupCheck(group, i)
                    addChip(data[i], group)
                }
                3 -> {
                    val group = view.findViewById<ChipGroup>(R.id.group4)
                    groupCheck(group, i)
                    addChip(data[i], group)
                }
            }
        }
    }

    private fun groupCheck(group: ChipGroup, i: Int) {
        group.setOnCheckedChangeListener { _, checkedId ->
            val checkView = try {
                group.children.filter { it.id == checkedId }.first()
            } catch (e: Exception) {
                null
            }
            val bean = checkView?.tag as? MemberAttrBean
            val ids = bean?.id ?: -1
            viewModel.attrCheck[i] = ids
        }
    }

    private fun addChip(data: List<MemberAttrBean>, group: ChipGroup) {
        for (attr in data) {
            val chip =
                LayoutInflater.from(context).inflate(R.layout.single_chip, group, false) as Chip
            chip.id = attr.ids
            chip.tag = attr
            chip.text = attr.text
            group.addView(chip)
        }
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