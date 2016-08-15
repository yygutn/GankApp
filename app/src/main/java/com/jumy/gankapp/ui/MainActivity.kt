package com.jumy.gankapp.ui

import android.os.Bundle
import com.jumy.gankapp.R
import com.jumy.gankapp.common.BaseActivity

class MainActivity : BaseActivity() {
    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun getMenuID(): Int {
        return R.menu.main
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("你好",true)
    }
}
