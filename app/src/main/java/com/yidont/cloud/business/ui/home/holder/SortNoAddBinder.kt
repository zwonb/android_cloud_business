package com.yidont.cloud.business.ui.home.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.MultiTypeAdapter
import com.google.android.material.chip.Chip
import com.yidont.cloud.business.R
import com.yidont.cloud.business.data.model.HomeBean

/**
 * 常用功能-已添加功能
 *
 * @author zwonb
 * @date 2019-12-19
 */
class SortNoAddBinder(val adapter1: MultiTypeAdapter) :
    ItemViewBinder<HomeBean, SortNoAddBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: HomeBean) {
    }


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_sort_function, parent, false), this)
    }

    class ViewHolder(
        itemView: View,
        binder: SortNoAddBinder
    ) : RecyclerView.ViewHolder(itemView) {

        val chip = itemView.findViewById<Chip>(R.id.chip)

        init {
            chip.setOnCloseIconClickListener {
                removeItem(binder)
            }
        }

        private fun removeItem(binder: SortNoAddBinder) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                binder.adapter.items.toMutableList()
                    .apply {
                        val bean = removeAt(position)
                        binder.adapter.items = this

                        addOtherRVItem(binder.adapter1, bean as HomeBean)
                    }
                binder.adapter.notifyItemRemoved(position)
            }
        }

        private fun addOtherRVItem(adapter1: MultiTypeAdapter, bean: HomeBean) {
            adapter1.items.toMutableList()
                .apply {
                    add(bean)
                    adapter1.items = this
                }
            adapter1.notifyItemInserted(adapter1.items.size - 1)
        }
    }
}