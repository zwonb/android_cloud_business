package com.yidont.store.ui

import com.yidont.http.viewmodel.BaseViewModel
import com.yidont.store.data.StoreRepository
import com.yidont.store.data.model.StoreHeadBean
import com.yidont.store.data.model.StoreTextBean

/**
 * @author zwonb
 * @date 2019-12-23
 */
class StoreMainVM(private val repository: StoreRepository) : BaseViewModel() {

    val storeData = mutableListOf<Any>().apply {
        add(StoreHeadBean("", "", ""))
        for (i in repository.mainImg.indices) {
            add(StoreTextBean(repository.mainImg[i], repository.mainText[i]))
        }
    }

}