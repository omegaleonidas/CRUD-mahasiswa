package com.sidiq.mahasiswa_app_sidiq.Repo

//import com.sidiq.mahasiswa_app_sidiq.View.ImputActivity
import com.sidiq.mahasiswa_app_sidiq.model.ResponseGetData
import com.sidiq.mahasiswa_app_sidiq.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository {

    fun getData(responHandler: (ResponseGetData) -> Unit, errorHandler: (Throwable) -> Unit) {

        NetworkModule().service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)

            })

    }


}