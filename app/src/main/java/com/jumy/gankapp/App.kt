package com.jumy.gankapp

import android.app.Application
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger
import kotlin.properties.Delegates

/**
 * User: Jumy (yygutn@gmail.com)
 * Date: 16/8/13  下午3:49
 */
class App : Application() {
    companion object {
        private var instance by Delegates.notNull<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //debug模式下输出日志，否则不输出
        if (BuildConfig.DEBUG) {
            Logger.init("Jumy").hideThreadInfo().methodOffset(0)
        } else {
            Logger.init("Jumy").logLevel(LogLevel.NONE)
        }
    }
}