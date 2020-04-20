package com.zhou.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer


class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.d("messageTag", "注册事件 ${LiveDataBus.get().getKey(this::class.java)}")
        LiveDataBus.get()
            .with(
                "${LiveDataBus.get().getKey(this::class.java)}",
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
            "${LiveDataBus.get().getKey(this::class.java)}"
        )
        LiveDataBus.get()
            .with(
                "${LiveDataBus.get().getKey(this::class.java)}",
                String::class.java
            )
            ?.postValue("第二个Activity发向第一个Activity")
    }

    fun finishSelf(view: View?) {
        finish()
    }

}


