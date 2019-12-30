package com.yidont.store.data.model

/**
 * @author zwonb
 * @date 2019-12-23
 */
data class StoreHeadBean(val img: String, val name: String, val date: String, val status: Int = 0)

data class StoreTextBean(val img: Int, val text: String)