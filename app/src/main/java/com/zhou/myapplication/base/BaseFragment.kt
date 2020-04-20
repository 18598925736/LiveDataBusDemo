package com.zhou.myapplication.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private lateinit var presenter: LiveDataPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LiveDataPresenter(this)
        lifecycle.addObserver(presenter)
    }

    fun sendLiveDataEvent(target: Class<out Any>, msg: String) {
        presenter.sendLiveDataEvent(target, msg)
    }
}