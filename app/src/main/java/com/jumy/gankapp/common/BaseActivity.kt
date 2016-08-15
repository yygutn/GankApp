package com.jumy.gankapp.common

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.Toast
import com.jumy.gankapp.BuildConfig
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * User: Jumy (yygutn@gmail.com)
 * Date: 16/8/13  下午6:42
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getLayoutID())
        initToolBar()
    }

    //初始化ToolBar
    private fun initToolBar() {
        setSupportActionBar(mToolBar)
    }
    //set Title && Home back action or not
    fun setTitle(titleString: String, showHome: Boolean) {
        title = titleString
        supportActionBar?.setDisplayShowHomeEnabled(showHome)
        supportActionBar?.setDisplayHomeAsUpEnabled(showHome)
    }

    /**
     * 获取视图的ID
     */
    abstract protected fun getLayoutID(): Int

    //视图绑定
    open fun viewBinding() {

    }

    open fun getMenuID(): Int {
        return -1
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getMenuID() > 0) {
            menuInflater.inflate(getMenuID(), menu)
        }
        return super.onCreateOptionsMenu(menu)
    }
    fun Activity.showToast(message: String,duration:Int){
        if (BuildConfig.DEBUG){
            Toast.makeText(this,message,duration).show()
        }
    }
    fun Activity.showLogE(message: String){
        Logger.e(message)
    }
    fun Activity.showLogD(message: String){
        Logger.d(message)
    }
    fun Activity.showLogW(message: String){
        Logger.w(message)
    }

}