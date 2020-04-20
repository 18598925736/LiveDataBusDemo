package com.zhou.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zhou.myapplication.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessageOnClick(view: View?) {
        sendLiveDataEvent(Main2Activity::class.java, "你好Main2Activity，我是MainActivity!")
    }

    fun jumpActivityOnClick(view: View?) {
        startActivity(Intent().setClass(this@MainActivity, Main2Activity::class.java))
    }
}
