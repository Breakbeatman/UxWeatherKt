package com.example.uxweatherkt.ui.baseView

import android.content.Context
import android.view.View
import com.example.uxweatherkt.ui.baseView.MyView

abstract class BaseView: MyView {

    lateinit var baseRootView: View

    override fun getRootView(): View? {
        return baseRootView
    }

    protected fun getContext(): Context {
        return getRootView()!!.context
    }
}