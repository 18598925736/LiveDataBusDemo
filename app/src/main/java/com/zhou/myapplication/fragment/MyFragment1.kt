package com.zhou.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.zhou.myapplication.R
import com.zhou.myapplication.activity.Main2Activity
import com.zhou.myapplication.base.BaseFragment

class MyFragment1 : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.myfragment1, container, false)
        init(root)
        return root
    }

    private fun init(root: View?) {
        root?.findViewById<Button>(R.id.btn_send_to_fragment2)?.setOnClickListener {
            sendLiveDataEvent(MyFragment2::class.java, "f2,f2，收到請回答，我是f1")
        }

        root?.findViewById<Button>(R.id.btn_send_to_activity)?.setOnClickListener {
            sendLiveDataEvent(Main2Activity::class.java, "activity,activity，收到請回答，我是f1")
        }

    }
}