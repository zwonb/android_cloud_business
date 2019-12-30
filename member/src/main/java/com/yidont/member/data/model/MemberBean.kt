package com.yidont.member.data.model

import android.view.View

/**
 * @author zwonb
 * @date 2019-12-20
 */
data class MemberFilterBean(
    val title: String,
    val id: Int = 0,
    val color: String,
    @Transient var status: Boolean = false
)

data class MemberAttrBean(val text: String, val id: Int = 0, val ids: Int = View.generateViewId())



