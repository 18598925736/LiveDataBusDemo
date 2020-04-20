package com.zhou.myapplication.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.zhou.myapplication.R
import com.zhou.myapplication.base.BaseFragment
import com.zhou.myapplication.ui.activity.Main2Activity

class MyFragment2 : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.myfragment2, container, false)
        init(root)
        return root
    }

    private fun init(root: View?) {
        root?.findViewById<Button>(R.id.btn_send_to_fragment1)?.setOnClickListener {
            sendLiveDataEvent(MyFragment1::class.java, "f1,f1，收到請回答，我是f2")
        }

        root?.findViewById<Button>(R.id.btn_send_to_activity)?.setOnClickListener {
            sendLiveDataEvent(Main2Activity::class.java, "activity,activity，收到請回答，我是f2")
        }

        tvMsg = root?.findViewById(R.id.tv_msg)
    }

    private var tvMsg: TextView? = null

    override fun handlerMsg(msg: Any?) {
        super.handlerMsg(msg)
        tvMsg?.text = msg.toString()
    }
}