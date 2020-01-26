package com.example.nikeurbandictionary.presenter

import com.example.nikeurbandictionary.model.Network
import com.example.nikeurbandictionary.model.classes.Definition
import com.example.nikeurbandictionary.view.MainActivity
import com.example.nikeurbandictionary.view.ViewContract

class Presenter : PresenterContract {

    var view: ViewContract? = null

    override fun initView(view: MainActivity) {
        Network().initRetrofit()
    }

    override fun destroyView() {
        this.view = view
    }

    override fun seeAll() {
        view = null
    }

    override fun sendData(dataSet: Definition) {
        view?.passData(dataSet)
    }

    override fun sendError(errorMessage: String) {
        view?.getErrorMessage(errorMessage)
    }
}