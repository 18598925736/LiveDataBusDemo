package com.zhou.myapplication.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

open class BaseActivity : AppCompatActivity() {

    private lateinit var presenter: LiveDataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LiveDataPresenter(this, Observer { msg ->
            handlerMsg(msg)
        })
        lifecycle.addObserver(presenter)
    }

    /**
     * 收到的msg可能是任意类型的
     */
    open fun handlerMsg(msg: Any?) {
        if (msg != null) {
            Log.d("messageTag", "${this::class.java}-> 收到消息:$msg")
        }
    }

    fun sendLiveDataEvent(target: Class<out Any>, msg: String) {
        presenter.sendLiveDataEvent(target, msg)
    }

}