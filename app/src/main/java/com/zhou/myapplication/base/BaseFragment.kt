package com.zhou.myapplication.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

open class BaseFragment : Fragment() {

    private lateinit var presenter: LiveDataPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LiveDataPresenter(this, Observer { msg ->
            handlerMsg(msg)
        })
        lifecycle.addObserver(presenter)
    }

    open fun handlerMsg(msg: Any?) {
        if (msg != null) {
            Log.d("messageTag", "${this::class.java}-> 收到消息:$msg")
        }
    }

    fun sendLiveDataEvent(target: Class<out Any>, msg: String) {
        presenter.sendLiveDataEvent(target, msg)
    }
}