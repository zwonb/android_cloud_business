package com.yidont.cloud.business.ui.home.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.yidont.cloud.business.R
import com.yidont.cloud.business.data.model.HomeBean

/**
 * 说明
 *
 * @author zwonb
 * @date 2019-12-19
 */
class HomeHolder : ItemViewDelegate<HomeBean, HomeHolder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: HomeBean) {

    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}