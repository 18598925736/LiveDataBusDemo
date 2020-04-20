package com.zhou.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.zhou.myapplication.base.BaseActivity
import com.zhou.myapplication.live.LiveDataBus


class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun sendMessageOnClick(view: View?) {
        sendLiveDataEvent(MainActivity::class.java, "你好MainActivity，我是Main2Activity!")
    }

    fun finishSelf(view: View?) {
        finish()
    }

}


