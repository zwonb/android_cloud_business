package com.yidont.lib.extension

import com.yidont.lib.provider.appContext
import com.zwonb.headbar.dp2px

/**
 * @author zwonb
 * @date 2019-12-20
 */

fun Int.dp(): Int = this.dp2px(appContext)
fun Float.dp(): Int = this.dp2px(appContext)

