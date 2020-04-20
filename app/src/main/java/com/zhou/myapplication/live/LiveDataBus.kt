package com.zhou.myapplication.live

import androidx.lifecycle.MutableLiveData

class LiveDataBus private constructor() {
    private val bus //防止重复注册，使用Map
            : MutableMap<String, MutableLiveData<Any>?>

    /**
     * 为了防止事件重复生效，事件注册的时候，必须加上事件序列号，每次用完一个序列号，都要自加1
     */
    private val busEventSerialization: MutableMap<String, Int>


    // 根据class来获得key
    fun getKey(clazz: Class<out Any>): String {
        return "Msg_${clazz.canonicalName}_${get().getSerial(clazz)}"
    }

    /**
     * 获取序列号, 序列号和事件的注册和发送密切相关
     */
    private fun getSerial(clazz: Class<out Any>): Int {
        val key = clazz.canonicalName!!

        return if (!busEventSerialization.containsKey(key)) {// 不存在，则添加
            busEventSerialization[key] = 0
            0 // 然后返回0
        } else {
            val current = busEventSerialization[key] // 若已存在，就直接返回
            current ?: 0// 如果是空，则报错
        }
    }

    /**
     * 序列号自加1
     */
    fun letSerialPlusSelf(clazz: Class<out Any>) {
        val key = clazz.canonicalName!!

        return if (!busEventSerialization.containsKey(key)) {
            busEventSerialization[key] = 0
        } else {
            busEventSerialization[key] = busEventSerialization[key]!! + 1
        }
    }

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
        busEventSerialization = HashMap()
    }
}