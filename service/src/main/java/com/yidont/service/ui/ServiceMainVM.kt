package com.yidont.service.ui

import com.yidont.http.viewmodel.BaseViewModel
import com.yidont.service.data.ServiceRepository
import com.yidont.service.data.model.*

/**
 * @author zwonb
 * @date 2019-12-23
 */
class ServiceMainVM(val repository: ServiceRepository) : BaseViewModel() {

    val bannerImgs = mutableListOf<String>().apply {
        add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521103500&di=4943eed915f993742b2f6f56848aed1f&src=http://pic.qiantucdn.com/58pic/18/52/35/62x58PICE7m_1024.jpg")
        add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521103500&di=4943eed915f993742b2f6f56848aed1f&src=http://pic.qiantucdn.com/58pic/18/52/35/62x58PICE7m_1024.jpg")
        add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521103500&di=4943eed915f993742b2f6f56848aed1f&src=http://pic.qiantucdn.com/58pic/18/52/35/62x58PICE7m_1024.jpg")
        add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521103500&di=4943eed915f993742b2f6f56848aed1f&src=http://pic.qiantucdn.com/58pic/18/52/35/62x58PICE7m_1024.jpg")
    }
    val serviceData = mutableListOf<Any>().apply {
        add(ServiceBannerBean(bannerImgs))
        add(ServiceTextBean("服务市场"))
        for (i in 0..4) {
            add(ServiceCardBean("", 0))
        }
        add(ServiceTextBean("官网教程"))
        add(ServiceTutorialBean(mutableListOf<TutorialBean>().apply {
            add(TutorialBean(""))
            add(TutorialBean(""))
            add(TutorialBean(""))
            add(TutorialBean(""))
        }))
    }

}