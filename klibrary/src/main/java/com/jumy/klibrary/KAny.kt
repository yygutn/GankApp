package com.jumy.klibrary

import com.orhanobut.logger.Logger

/**
 * Created by Jumy on 16/8/22 16:44.
 * Copyright (c) 2016, yygutn@gmail.com All Rights Reserved.
 */


fun Any.showLogE(message: String) {
    Logger.e(message)
}

fun Any.showLogE(tag: String, message: String) {
    Logger.e(message)
}


fun Any.showLogD(message: String) {
    Logger.d(message)
}

fun Any.showLogD(tag: String, message: String) {
    Logger.d(message)
}

fun Any.showLogW(message: String) {
    Logger.w(message)
}

fun Any.showLogW(tag: String, message: String) {
    Logger.w(message)
}