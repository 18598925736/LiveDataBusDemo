package com.zhou.myapplication.base

import android.util.Log
import androidx.lifecycle.Observer
import com.zhou.myapplication.Main2Activity
import com.zhou.myapplication.interfaces.BasePresenterInterface
import com.zhou.myapplication.live.LiveDataBus

/**
 * LiveDataBus的P类
 */
class LiveDataPresenter : BasePresenterInterface {

    private val activity: BaseActivity

    constructor(activity: BaseActivity) {
        this.activity = activity
    }

    override fun onCreate() {
        // 自动感知 onCreate 方法
        Log.d("MainPresenter", "onCreate")
        registerEventBus()
    }

    private fun registerEventBus() {
        Log.d("messageTag", "注册事件 -> ${LiveDataBus.get().getKey(activity::class.java)}")
        LiveDataBus.get()
            .with(
                "${LiveDataBus.get().getKey(activity::class.java)}",
                String::class.java
            )
            ?.observe(activity, Observer { str ->
                if (str != null) {
                    Log.d("messageTag", "${activity::class.java} 收到消息$str")
                }
            })
    }

    fun sendLiveDataEvent(targetActivity: Class<out Any>, msg: String) {
        Log.d(
            "messageTag",
            "发送事件：${LiveDataBus.get().getKey(targetActivity)}"
        )
        LiveDataBus.get()
            .with(
                "${LiveDataBus.get().getKey(targetActivity)}",
                String::class.java
            )
            ?.postValue(msg)
    }


    override fun onDestroy() {
        // 自动感知 onDestroy 方法
        Log.d("MainPresenter", "onDestroy")
        LiveDataBus.get().letSerialPlusSelf(activity::class.java)
    }
}