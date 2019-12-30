package com.yidont.member.ui

import androidx.lifecycle.MutableLiveData
import com.yidont.http.data.HttpResult
import com.yidont.http.viewmodel.BaseViewModel
import com.yidont.member.data.model.MemberFilterBean
import com.yidont.member.data.repository.MemberRepository

/**
 * @author zwonb
 * @date 2019-12-20
 */
class MemberMainViewModel(private val repository: MemberRepository) : BaseViewModel() {

    val gradeData = MutableLiveData<List<MemberFilterBean>>()
    val labelData = MutableLiveData<List<MemberFilterBean>>()

    val attrData by lazy(LazyThreadSafetyMode.NONE) { repository.attrData }
    val attrCheck = mutableListOf<Int>().apply {
        add(-1)
        add(-1)
        add(-1)
        add(-1)
    }

    val gradeLive = MutableLiveData<MemberFilterBean>()
    val labelLive = MutableLiveData<MemberFilterBean>()
    // 0会员等级 1会员标签
    var filterType = 0

    val memberData by lazy(LazyThreadSafetyMode.NONE) {
        mutableListOf<Any>().apply {
//            for (i in 0..10) {
//                add(MemberFilterBean("", 0, true))
//            }
        }
    }

    var isLoading = false

    fun requestGrade() {
        tryLaunch({
            val response = repository.requestGrade()
            when (val result = response.result()) {
                is HttpResult.Success -> {
                    gradeData.value = result.data
                }
            }
        })
    }

    fun requestLabel() {
        tryLaunch({
            val response = repository.requestLabel()
            when (val result = response.result()) {
                is HttpResult.Success -> {
                    labelData.value = result.data
                }
            }
        })
    }


}