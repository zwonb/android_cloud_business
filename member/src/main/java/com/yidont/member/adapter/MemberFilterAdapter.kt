package com.yidont.member.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.yidont.member.R
import com.yidont.member.data.model.MemberFilterBean

/**
 * 会员弹窗筛选 adapter
 *
 * @author zwonb
 * @date 2019-12-20
 */
class MemberFilterAdapter(private val data: List<MemberFilterBean>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context = parent?.context
        var view = convertView
        val holder = if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_member_filter, parent, false)
            val holder = ViewHolder()
            holder.text = view.findViewById(R.id.text)
            holder.img = view.findViewById(R.id.img)
            view.tag = holder
            holder
        } else {
            view!!.tag as ViewHolder
        }
        holder.text.text = data[position].title

        if (data[position].status) {
            holder.img.visibility = View.VISIBLE
        } else {
            holder.img.visibility = View.GONE
        }
        return view!!
    }

    override fun getItem(position: Int): MemberFilterBean {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    class ViewHolder {
        lateinit var text: TextView
        lateinit var img: ImageView
    }
}