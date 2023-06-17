package org.d3if00001.catatanapp.data.remote


import com.google.gson.annotations.SerializedName

data class Requests(
    @SerializedName("available")
    val available: Int, // 9999
    @SerializedName("resets")
    val resets: String, // 2023-07-01 00:00:00
    @SerializedName("used")
    val used: Int // 1
)