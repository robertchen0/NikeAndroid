package com.example.nikeurbandictionary.presenter

import com.example.nikeurbandictionary.model.classes.Definition
import com.example.nikeurbandictionary.view.MainActivity

interface PresenterContract {
    fun initView(view: MainActivity)
    fun destroyView()
    fun seeAll()
    fun sendData(dataSet: Definition)
    fun sendError(errorMessage: String)
}