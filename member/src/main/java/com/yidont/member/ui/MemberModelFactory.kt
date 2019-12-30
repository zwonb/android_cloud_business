package com.yidont.member.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yidont.member.data.repository.MemberRepository

/**
 * @author zwonb
 * @date 2019-12-05
 */
class MemberModelFactory(private val repository: MemberRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberMainViewModel(repository) as T
    }
}