package org.d3if00001.catatanapp.data.remote.models


import com.google.gson.annotations.SerializedName

data class HolidayResponse(
    @SerializedName("holidays")
    val holidays: List<Holiday>,
    @SerializedName("requests")
    val requests: Requests,
    @SerializedName("status")
    val status: Int, // 200
    @SerializedName("warning")
    val warning: String
)