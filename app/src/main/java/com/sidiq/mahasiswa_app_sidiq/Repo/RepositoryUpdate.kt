package com.sidiq.mahasiswa_app_sidiq.Repo

import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryUpdate {

    fun getUpdatetdata(
        id: String,
        nama: String,
        nobp: String,
        alamat: String,
        responHandler: (ResponseAction) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        NetworkModule().service().updateData(id, nama, nobp, alamat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }
}