package com.yidont.member.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.yidont.member.R
import com.yidont.member.data.model.MemberFilterBean

/**
 * 会员
 *
 * @author zwonb
 * @date 2019-12-20
 */
class MemberBinder(val click: (MemberFilterBean) -> Unit) : ItemViewBinder<MemberFilterBean, MemberBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: MemberFilterBean) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_member, parent, false),
            this
        )
    }

    class Holder(item: View, binder: MemberBinder) : RecyclerView.ViewHolder(item) {

        init {
            item.setOnClickListener {
                binder.click(binder.adapter.items[adapterPosition] as MemberFilterBean)
            }
        }
    }
}