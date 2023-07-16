package com.example.springsecuritystudy.domain.admin

enum class YesNo {
    YES, NO;

    val isYes: Boolean
        get() = this == YES

    val isNo: Boolean
        get() = this == NO
}
