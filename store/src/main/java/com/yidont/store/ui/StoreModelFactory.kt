package com.yidont.store.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yidont.store.data.StoreRepository

/**
 * @author zwonb
 * @date 2019-12-05
 */
class StoreModelFactory(private val repository: StoreRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoreMainVM(repository) as T
    }
}