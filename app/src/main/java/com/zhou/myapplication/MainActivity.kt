package com.zhou.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 注册事件
         *
         * 参数 MainActivity是 事件key，Huawei.class是一个自定义类，表明：我只响应key为MainActivity
         * onChange方法重写，
         *
         */
        Log.d("messageTag", "注册事件 ${eventKey}${busEventSerialNum}")
        LiveDataBus.get()
            .with(
                "${eventKey}${LiveDataBus.get().getSerial(this::class.java)}",
                String::class.java
            )
            ?.observe(this, Observer { str ->
                if (str != null) {
                    Log.d("messageTag", "MainActivity:收到消息$str")
                }
            })
    }

    fun sendMessageOnClick(view: View?) {
        Log.d(
            "messageTag",
            "发送事件 Msg_${Main2Activity::class.java.canonicalName}_${LiveDataBus.get().getSerial(
                Main2Activity::class.java
            )}"
        )
        LiveDataBus.get()
            .with(
                "Msg_${Main2Activity::class.java.canonicalName}_${LiveDataBus.get().getSerial(Main2Activity::class.java)}",
                String::class.java
            )
            ?.postValue("MainActivity 发到TwoActivity")
    }

    fun jumpActivityOnClick(view: View?) {
        startActivity(Intent().setClass(this@MainActivity, Main2Activity::class.java))
    }
}
