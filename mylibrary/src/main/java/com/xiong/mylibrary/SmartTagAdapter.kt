package com.xiong.mylibrary

import android.view.View

/**
 * @Author Administrator
 * @Date 2020/7/23 10:00
 */
abstract class SmartTagAdapter<T> {
    private var tabDatas: MutableList<T>

    constructor(tabDatas: MutableList<T>) {
        this.tabDatas = tabDatas
    }


    var mOnDataChangeListener: OnDataChangeListener? = null

    interface OnDataChangeListener {
        fun onDataChange()
    }

    fun setOnDataChageListener(onDataChangeListener: OnDataChangeListener) {
        this.mOnDataChangeListener = onDataChangeListener
    }

    fun getCount(): Int {
        return tabDatas.size
    }

    fun notifyDataChanged() {
        mOnDataChangeListener?.onDataChange()
    }

    fun getItem(position: Int): T {
        return tabDatas[position]
    }

    abstract fun getView(parent: SmartTagLayout<T>, position: Int, data: T): View

}