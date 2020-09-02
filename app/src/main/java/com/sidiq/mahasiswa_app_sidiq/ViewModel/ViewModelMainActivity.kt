package com.sidiq.mahasiswa_app_sidiq.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sidiq.mahasiswa_app_sidiq.Repo.Repository
import com.sidiq.mahasiswa_app_sidiq.Repo.RepositotyDelete
import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.model.ResponseGetData

class ViewModelMainActivity : ViewModel() {

    val repository = Repository()
    val repositoryDelete =RepositotyDelete()

    val responseData = MutableLiveData<ResponseGetData>()
    val responseDeleteData = MutableLiveData<ResponseAction>()
    val isError = MutableLiveData<Throwable>()

    fun getListData() {
        repository.getData({
            responseData.value = it
        }, {
            isError.value = it
        })
    }

    fun getDeleteData(id:String?) {
        repositoryDelete.getDelete(id?:"",{
            responseDeleteData.value = it
        }, {
            isError.value = it
        })
    }

}
