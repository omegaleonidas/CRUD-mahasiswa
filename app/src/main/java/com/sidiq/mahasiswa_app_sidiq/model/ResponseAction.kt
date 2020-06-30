package com.sidiq.mahasiswa_app_sidiq.model

import com.google.gson.annotations.SerializedName

data class ResponseAction (

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("isSuccess")
    val isSuccess: Boolean? = null
)