package com.zhou.myapplication

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    // liveDataBus相关   本类name
    val busEventSerialNum = LiveDataBus.get().getSerial(this::class.java)

    val eventKey = "Msg_${this.javaClass.canonicalName}_"

    override fun onDestroy() {
        super.onDestroy()
        LiveDataBus.get().letSerialPlusSelf(this::class.java)
    }
}