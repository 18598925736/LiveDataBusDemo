package com.zhou.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import com.zhou.myapplication.R
import com.zhou.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            sendLiveDataEvent(Main2Activity::class.java, "你好Main2Activity，我是MainActivity!")
        }

        btn2.setOnClickListener {
            startActivity(Intent().setClass(this@MainActivity, Main2Activity::class.java))
        }
    }

    override fun handlerMsg(msg: Any?) {
        super.handlerMsg(msg)
        tv_msg.text = msg.toString()
    }
}
