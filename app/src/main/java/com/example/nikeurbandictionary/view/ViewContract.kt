package com.example.nikeurbandictionary.view

import com.example.nikeurbandictionary.model.classes.Definition

interface ViewContract {

    fun initUI()
    fun initPresenter()
    fun passData(dataSet: Definition)
    fun getErrorMessage(errorMessage: String)

}