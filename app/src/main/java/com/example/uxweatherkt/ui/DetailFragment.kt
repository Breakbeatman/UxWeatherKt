package com.example.uxweatherkt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.uxweatherkt.R
import com.example.uxweatherkt.ui.detailForecastViews.DetailForecastView
import com.example.uxweatherkt.ui.detailForecastViews.DetailForecastViewImpl

class DetailFragment : Fragment() {

    private lateinit var detailForecastView: DetailForecastView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        initViews(view)
        return view
    }

    private fun initViews(baseRootView: View) {
        detailForecastView = DetailForecastViewImpl(baseRootView)
    }
}
