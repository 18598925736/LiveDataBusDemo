package com.zhou.myapplication.base

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zhou.myapplication.interfaces.BasePresenterInterface
import com.zhou.myapplication.live.LiveDataBus

/**
 * LiveDataBus的P类
 */
class LiveDataPresenter : BasePresenterInterface {

    private val owner: LifecycleOwner

    constructor(owner: LifecycleOwner) {
        this.owner = owner
    }

    override fun onCreate() {
        // 自动感知 onCreate 方法
        Log.d("MainPresenter", "onCreate")
        registerEventBus()
    }

    private fun registerEventBus() {
        Log.d("messageTag", "注册事件 -> ${LiveDataBus.get().getKey(owner::class.java)}")
        LiveDataBus.get()
            .with(
                "${LiveDataBus.get().getKey(owner::class.java)}",
                String::class.java
            )
            ?.observe(owner, Observer { str ->
                if (str != null) {
                    Log.d("messageTag", "${owner::class.java}-> 收到消息:$str")
                }
            })
    }

    fun sendLiveDataEvent(target: Class<out Any>, msg: String) {
        Log.d(
            "messageTag",
            "发送事件：${LiveDataBus.get().getKey(target)}"
        )
        LiveDataBus.get()
            .with(
                "${LiveDataBus.get().getKey(target)}",
                String::class.java
            )
            ?.postValue(msg)
    }


    override fun onDestroy() {
        // 自动感知 onDestroy 方法
        Log.d("MainPresenter", "onDestroy")
        LiveDataBus.get().letSerialPlusSelf(owner::class.java)
    }
}