package com.example.uxweatherkt.ui

import android.content.Context
import android.view.View

abstract class BaseView: MyView {

    lateinit var baseRootView: View

    override fun getRootView(): View? {
        return baseRootView
    }

    protected fun getContext(): Context {
        return getRootView()!!.context
    }

    protected fun findViewById(id: Int): View? {
        return getRootView()!!.findViewById(id)
    }

//    protected open fun <T : View?> findViewById(id: Int): T {
//        return getRootView()!!.findViewById(id)
//    }
}