package com.yidont.member.data.repository

import com.yidont.http.httpCreate
import com.yidont.lib.util.SPUtil
import com.yidont.member.data.Api
import com.yidont.member.data.model.MemberAttrBean

/**
 * @author zwonb
 * @date 2019-12-20
 */
class MemberRepository {

    val attrData = mutableListOf<List<MemberAttrBean>>().apply {
        add(mutableListOf<MemberAttrBean>().apply {
            add(MemberAttrBean("不限", 0))
            add(MemberAttrBean("男", 1))
            add(MemberAttrBean("女", 2))
        })
        add(mutableListOf<MemberAttrBean>().apply {
            add(MemberAttrBean("18-30", 0))
            add(MemberAttrBean("30-40", 1))
            add(MemberAttrBean("40-50", 2))
            add(MemberAttrBean("50以上", 3))
        })
        add(mutableListOf<MemberAttrBean>().apply {
            add(MemberAttrBean("今天", 0))
            add(MemberAttrBean("未来3天", 1))
            add(MemberAttrBean("未来7天", 2))
        })
        add(mutableListOf<MemberAttrBean>().apply {
            add(MemberAttrBean("7天内", 0))
            add(MemberAttrBean("15天内", 1))
            add(MemberAttrBean("30天内", 2))
            add(MemberAttrBean("30天以上", 3))
        })
    }

    suspend fun requestGrade() = httpCreate<Api>().searchMember("level", SPUtil.getPhone())

    suspend fun requestLabel() = httpCreate<Api>().searchMember("label", SPUtil.getPhone())

    fun searchMember() {
//httpCreate<>()
    }
}