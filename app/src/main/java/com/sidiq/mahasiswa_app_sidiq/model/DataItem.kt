package com.sidiq.mahasiswa_app_sidiq.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(
    @field:SerializedName("mahasiswa_alamat")
    val mahasiswaAlamat: String? = null,

    @field:SerializedName("mahasiswa_nobp")
    val mahasiswaNobp: String? = null,

    @field:SerializedName("id_mahasiswa")
    val idMahasiswa: String? = null,

    @field:SerializedName("mahasiswa_nama")
    val mahasiswaNama: String? = null
) : Parcelable
