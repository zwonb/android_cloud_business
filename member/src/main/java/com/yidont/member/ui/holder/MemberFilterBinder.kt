package com.yidont.member.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.yidont.member.R
import com.yidont.member.data.model.MemberFilterBean

/**
 * 会员筛选
 *
 * @author zwonb
 * @date 2019-12-20
 */
class MemberFilterBinder(val click: (MemberFilterBean) -> Unit) : ItemViewBinder<MemberFilterBean, MemberFilterBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: MemberFilterBean) {
        holder.text.text = item.title
        if (item.status) {
            holder.img.visibility = View.VISIBLE
        } else {
            holder.img.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_member_filter, parent, false),
            this
        )
    }

    class Holder(item: View, binder: MemberFilterBinder) : RecyclerView.ViewHolder(item) {
        val text = item.findViewById<TextView>(R.id.text)
        val img = item.findViewById<View>(R.id.img)

        init {
            item.setOnClickListener {
                binder.click(binder.adapter.items[adapterPosition] as MemberFilterBean)
            }
        }
    }
}