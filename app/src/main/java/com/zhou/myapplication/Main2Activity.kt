package com.zhou.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


/**
 * 作者：haoLin_Lee on 2019/04/25 22:40
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
class Main2Activity : AppCompatActivity() {

    companion object {
        var seri = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        LiveDataBus.get()
            .with("MessageActvity2_$seri", String::class.java)
            ?.observe(this, Observer { str ->
                if (str != null) {
                    Log.d("messageTag", "TwoActivity:收到消息$str")
                }
            })
    }

    fun sendMessageOnClick(view: View?) {
        LiveDataBus.get()
            .with("MessageActvity1", String::class.java)
            ?.postValue("第二个Activity发向第一个Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        seri++
    }

}


