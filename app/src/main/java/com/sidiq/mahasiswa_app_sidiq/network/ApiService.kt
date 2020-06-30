package com.sidiq.mahasiswa_app_sidiq.network

import com.sidiq.mahasiswa_app_sidiq.model.ResponseAction
import com.sidiq.mahasiswa_app_sidiq.model.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("getData.php")
    fun getData(): Call<ResponseGetData>


    //GetDataById
    @GET("getData.php")
    fun getDataById(@Query("id_mahasiswa") id: String): Call<ResponseGetData>


    //insert data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(
        @Field("mahasiswa_nama") mahasiswa_nama: String
        , @Field("mahasiswa_nobp") mahasiswa_nobp: String
        , @Field("mahasiswa_alamat") mahasiswa_alamat: String
    ): Call<ResponseAction>

    //update datra
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(
        @Field("id_mahasiswa") imahasiswa_nama: String,
        @Field("mahasiswa_nama") mahasiswa_nama: String,
        @Field("mahasiswa_nobp") mahasiswa_nobp: String,
        @Field("mahasiswa_alamat") mahasiswa_alamat:String
    ): Call<ResponseAction>

    //delete data
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(
        @Field("id_mahasiswa")id_mahasiswa:String
    ): Call<ResponseAction>
}