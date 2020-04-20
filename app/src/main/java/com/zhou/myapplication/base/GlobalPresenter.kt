package com.zhou.myapplication.base

import android.app.Activity
import android.util.Log
import com.zhou.myapplication.live.LiveDataBus
import com.zhou.myapplication.interfaces.BasePresenterInterface

class GlobalPresenter : BasePresenterInterface {

    private val activity: Activity

    constructor(activity: Activity) {
        this.activity = activity
    }

    override fun onCreate() {
        // 自动感知 onCreate 方法
        Log.d("MainPresenter", "onCreate")
    }

    override fun onDestroy() {
        // 自动感知 onDestroy 方法
        Log.d("MainPresenter", "onDestroy")
        LiveDataBus.get().letSerialPlusSelf(activity::class.java)
    }
}