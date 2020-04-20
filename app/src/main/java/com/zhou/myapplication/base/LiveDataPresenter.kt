package com.zhou.myapplication.base

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zhou.myapplication.interfaces.BasePresenterInterface

/**
 * LiveDataBus的P类
 */
class LiveDataPresenter : BasePresenterInterface {

    private val owner: LifecycleOwner // 所谓LifecycleOwner是一个接口，它的实现类，其实就两个，一个Fragment，一个Activity,
    // 用一个接口消除两者之间的沟通差异，这也算一种解耦思维，接口解耦！
    private val ob: Observer<Any?>

    constructor(owner: LifecycleOwner, ob: Observer<Any?>) {
        this.owner = owner
        this.ob = ob
    }

    override fun onCreate() {
        // 自动感知 onCreate 方法
        Log.d("MainPresenter", "onCreate")
        registerEventBus()
    }

    private fun registerEventBus() {
        Log.d("messageTag", "注册事件 -> ${LiveDataBus.get().getKey(owner::class.java)}")
        LiveDataBus.get()
            .with<String>("${LiveDataBus.get().getKey(owner::class.java)}")
            ?.observe(owner, ob)
    }

    /**
     * @param target 目标
     * @param msg 消息
     */
    fun sendLiveDataEvent(target: Class<out Any>, msg: String) {
        Log.d(
            "messageTag",
            "发送事件：${LiveDataBus.get().getKey(target)}"
        )
        LiveDataBus.get()
            .with<String>("${LiveDataBus.get().getKey(target)}")
            ?.postValue(msg)
    }


    override fun onDestroy() {
        // 自动感知 onDestroy 方法
        Log.d("MainPresenter", "onDestroy")
        LiveDataBus.get().letSerialPlusSelf(owner::class.java)
    }
}