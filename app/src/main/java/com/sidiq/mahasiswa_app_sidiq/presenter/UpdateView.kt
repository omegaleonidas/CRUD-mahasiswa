package com.sidiq.mahasiswa_app_sidiq.presenter

import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction

interface UpdateView {

    fun successUpdate(response : ResponseAction)
    fun errorUpdate(msg : String)
}