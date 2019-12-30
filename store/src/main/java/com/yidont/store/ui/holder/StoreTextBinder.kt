package com.yidont.store.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.yidont.store.R
import com.yidont.store.data.model.StoreTextBean

/**
 * 门店-item
 *
 * @author zwonb
 * @date 2019-12-23
 */
class StoreTextBinder() : ItemViewBinder<StoreTextBean, StoreTextBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: StoreTextBean) {
        holder.setData(item)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_img_text_hor, parent, false)
        )
    }

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val img = itemView.findViewById<ImageView>(R.id.item_img)
        private val text = itemView.findViewById<TextView>(R.id.item_text)

        fun setData(bean: StoreTextBean) {
            img.setImageResource(bean.img)
            text.text = bean.text
        }
    }
}