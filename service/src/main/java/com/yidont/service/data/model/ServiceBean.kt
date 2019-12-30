package com.yidont.service.data.model

/**
 * @author zwonb
 * @date 2019-12-23
 */
data class ServiceBannerBean(val img: List<String>)

data class ServiceTextBean(val text: String)
data class ServiceCardBean(val text: String, val img: Int)
data class ServiceTutorialBean(val img: List<TutorialBean>)

data class TutorialBean(val text: String)