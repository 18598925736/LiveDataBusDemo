package com.zhou.myapplication

import androidx.lifecycle.MutableLiveData
import java.util.*

class LiveDataBus private constructor() {
    private val bus //防止重复注册，使用Map
            : MutableMap<String, MutableLiveData<Any>?>

    private object SingletonHolder {
        val LIVE_DATA_BUS =
            LiveDataBus() // 这个也是一种单例的写法,静态内部类
    }

    /**
     * 创建一个 观察者，也就是注册事件
     *
     * @param key
     * @param type
     * @param <T>
     * @return
    </T> */
    @Synchronized
    fun <T> with(key: String, type: Class<T>?): MutableLiveData<T>? {
        if (!bus.containsKey(key)) { // 不包含，则添加
            bus[key] = MutableLiveData()
        }
        return bus[key] as MutableLiveData<T>? // 包含，则直接返回
    }

    companion object {
        /**
         * 单例的getInstance方法
         *
         * @return
         */
        fun get(): LiveDataBus {
            return SingletonHolder.LIVE_DATA_BUS
        }
    }

    init {
        bus = HashMap()
    }
}