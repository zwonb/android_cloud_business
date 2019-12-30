package com.yidont.store.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.yidont.store.R
import com.yidont.store.data.model.StoreHeadBean

/**
 * 门店-头部
 *
 * @author zwonb
 * @date 2019-12-23
 */
class StoreHeadBinder() : ItemViewBinder<StoreHeadBean, StoreHeadBinder.Holder>() {

    override fun onBindViewHolder(holder: Holder, item: StoreHeadBean) {
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): Holder {
        return Holder(
            inflater.inflate(R.layout.item_store_head, parent, false)
        )
    }

    class Holder(item: View) : RecyclerView.ViewHolder(item) {

    }
}