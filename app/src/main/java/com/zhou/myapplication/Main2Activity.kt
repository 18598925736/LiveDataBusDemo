package com.zhou.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer


/**
 * 作者：haoLin_Lee on 2019/04/25 22:40
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.d("messageTag", "注册事件 ${eventKey}${busEventSerialNum}")
        LiveDataBus.get()
            .with(
                "${eventKey}${busEventSerialNum}",
                String::class.java
            )
            ?.observe(this, Observer { str ->
                if (str != null) {
                    Log.d("messageTag", "TwoActivity:收到消息$str")
                }
            })
    }

    fun sendMessageOnClick(view: View?) {
        Log.d(
            "messageTag",
            "发送事件 Msg_${MainActivity::class.java.canonicalName}_${LiveDataBus.get().getSerial(
                MainActivity::class.java
            )}"
        )
        LiveDataBus.get()
            .with(
                "Msg_${MainActivity::class.java.canonicalName}_${LiveDataBus.get().getSerial(
                    MainActivity::class.java
                )}",
                String::class.java
            )
            ?.postValue("第二个Activity发向第一个Activity")
    }

    fun finishSelf(view: View?) {
        finish()
    }

}


