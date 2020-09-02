package com.sidiq.mahasiswa_app_sidiq.Repo

import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositotyDelete {

    fun getDelete(
        id: String, responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit
    ) {
        NetworkModule().service().deleteData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }
}