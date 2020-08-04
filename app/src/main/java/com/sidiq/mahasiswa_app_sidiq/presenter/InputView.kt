package com.sidiq.mahasiswa_app_sidiq.presenter

import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction

interface InputView {
    fun successInput(response : ResponseAction)
    fun errorInput(msg : String)
}