package com.sidiq.mahasiswa_app_sidiq.presenter

import com.sidiq.mahasiswa_app_sidiq.ImputActivity
import com.sidiq.mahasiswa_app_sidiq.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterInput(val inputView: ImputActivity) {

    fun inputData(nama: String, nohp: String, alamat: String) {

        NetworkModule().service().insertData(nama, nohp, alamat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                inputView.successInput(it)
            }, {

                inputView.errorInput(it.localizedMessage)
            })

    }


}
