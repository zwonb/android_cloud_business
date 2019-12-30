package com.yidont.lib.util

/**
 * 正则匹配
 *
 * @author zwonb
 * @date 2019-12-17
 */

const val REGEX_PHONE =
    "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}\$"

fun CharSequence?.isPhone() = if (this.isNullOrBlank()) {
    false
} else {
    this.matches(Regex(REGEX_PHONE))
}