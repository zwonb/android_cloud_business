package com.yidont.service.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yidont.service.data.ServiceRepository

/**
 * @author zwonb
 * @date 2019-12-05
 */
class ServiceModelFactory(private val repository: ServiceRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ServiceMainVM(repository) as T
    }
}