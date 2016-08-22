package com.jumy.gankapp.common

import android.app.ActivityManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.jumy.gankapp.App
import com.jumy.klibrary.showLogD
import com.orhanobut.logger.Logger
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Jumy on 16/8/22 16:08.
 * Copyright (c) 2016, yygutn@gmail.com All Rights Reserved.
 */
class AppManager {
    companion object {
        val TAG = "AppManager"
        val mActivityStack = Stack<WeakReference<AppCompatActivity>>()
        fun getStackSize() = mActivityStack.size
        val manager = AppManager()
    }

    private fun logStackInfo() {
        var message = "StackSize = " + (mActivityStack.size) + "\n"
        for (ac in mActivityStack) {
            message += ac.javaClass.simpleName + "\n"
        }
        Logger.t(TAG).w(message)
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: AppCompatActivity) {
        mActivityStack.add(WeakReference(activity))
        showLogD(TAG, "add " + activity.localClassName + "\n" + "current size is : " + mActivityStack.size)
        logStackInfo()
    }

    //获取当前Activity（堆栈中最后一个压入的）
    fun getCurActivity() = mActivityStack.lastElement().get()

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishCurActivity() {
        mActivityStack.pop().get().finish()
        logStackInfo()
    }

    //结束指定Activity
    fun finishActivity(activity: AppCompatActivity) {
        var message = ""
        message += "Finishing " + activity.javaClass.simpleName + "\n"
        message += "Before finish, the Stack size is :" + AppManager.getStackSize() + "\n"
        removeActivity(activity.javaClass)
        message += "After finished, the Stack size is :" + AppManager.getStackSize() + "\n"
        showLogD(TAG, message)
    }

    //移除堆栈信息并结束页面
    private fun removeActivity(javaClass: Class<AppCompatActivity>) {
        mActivityStack.forEach { ref ->
            if (ref.get().javaClass.simpleName.equals(javaClass.simpleName)) {
                mActivityStack.remove(ref)
                ref.get().finish()
                ref.clear()
            }
        }
    }

    // close all
    fun finishAll() {
        while (!mActivityStack.empty()) {
            mActivityStack.pop().get().finish()
        }
    }

    //exit app
    fun AppExit() {
        finishAll()
        val am = App.AppContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        am.killBackgroundProcesses(App.AppContext.packageName)
        System.exit(0)
    }
}