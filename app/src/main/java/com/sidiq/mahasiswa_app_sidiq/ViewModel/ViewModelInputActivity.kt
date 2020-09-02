package com.sidiq.mahasiswa_app_sidiq.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sidiq.mahasiswa_app_sidiq.Repo.Repository
import com.sidiq.mahasiswa_app_sidiq.Repo.RepositoryInsert
import com.sidiq.mahasiswa_app_sidiq.Repo.RepositoryUpdate
import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction

class ViewModelInputActivity : ViewModel() {
    val repositoryInsert = RepositoryInsert()
    val repositoryUpdate = RepositoryUpdate()

    val responseData = MutableLiveData<ResponseAction>()
    val isError = MutableLiveData<Throwable>()

    fun getListDataInsert(nama: String, nobp: String, alamat: String) {
        repositoryInsert.getInsertdata(nama, nobp, alamat, {
            responseData.value = it
        }, {
            isError.value = it
        })
    }

    fun getListDataUpdate(id: String, nama: String, nobp: String, alamat: String) {
        repositoryUpdate.getUpdatetdata(id, nama, nobp, alamat, {
            responseData.value = it
        }, {
            isError.value = it
        })
    }


}






