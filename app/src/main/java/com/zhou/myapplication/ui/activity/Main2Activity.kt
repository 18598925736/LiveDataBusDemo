package com.zhou.myapplication.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhou.myapplication.R
import com.zhou.myapplication.base.BaseActivity
import com.zhou.myapplication.ui.fragment.MyFragment1
import com.zhou.myapplication.ui.fragment.MyFragment2
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initFragments()

        btn1.setOnClickListener {
            sendLiveDataEvent(MainActivity::class.java, "你好MainActivity，我是Main2Activity!")
        }

        btn2.setOnClickListener {
            sendLiveDataEvent(MyFragment1::class.java, "你好Fragment1，我是Main2Activity!")
        }
    }

    /**
     * 初始化fragment
     */
    private fun initFragments() {
        showFragment(MyFragment1(), R.id.fl_fragment1)
        showFragment(MyFragment2(), R.id.fl_fragment2)
    }

    private fun showFragment(fragment: Fragment, contentFrame: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(contentFrame, fragment)
        beginTransaction.show(fragment)
        beginTransaction.commitAllowingStateLoss()
    }

    override fun handlerMsg(msg: Any?) {
        super.handlerMsg(msg)
        tv_msg.text = msg.toString()
    }
}


