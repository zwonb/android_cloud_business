package com.yidont.lib.callback

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


abstract class RVScrollListener(private val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy < 0 || isDataLoading() || isNoMoreData()) return

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager.itemCount
        var firstVisibleItem: Int? = null
        if (layoutManager is LinearLayoutManager) {
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val items = layoutManager.findFirstVisibleItemPositions(null)
            firstVisibleItem = items.min()
        }
        if (firstVisibleItem != null) {
            if (totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
                onLoadMore()
            }
        }
    }

    abstract fun onLoadMore()

    abstract fun isDataLoading(): Boolean

    abstract fun isNoMoreData(): Boolean

    companion object {
        private const val VISIBLE_THRESHOLD = 1
    }

}