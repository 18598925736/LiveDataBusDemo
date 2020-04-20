package com.zhou.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private lateinit var presenter: GlobalPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = GlobalPresenter(this)
        lifecycle.addObserver(presenter)
    }

}