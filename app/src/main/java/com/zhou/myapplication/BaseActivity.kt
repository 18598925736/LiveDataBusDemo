package com.zhou.myapplication

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    // liveDataBus相关   本类name
    val busEventSerialKey = LiveDataBus.get().getSerial(this::class.java)

    override fun onDestroy() {
        super.onDestroy()
        LiveDataBus.get().letSerialPlusSelf(this::class.java)
    }
}