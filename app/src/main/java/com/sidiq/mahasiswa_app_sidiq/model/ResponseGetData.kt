package com.sidiq.mahasiswa_app_sidiq.model

import com.google.gson.annotations.SerializedName


class ResponseGetData (

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
)