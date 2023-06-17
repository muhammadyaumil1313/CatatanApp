package org.d3if00001.catatanapp.data.remote


import com.google.gson.annotations.SerializedName

data class HolidayResponse(
    @SerializedName("holidays")
    val holidays: List<Holiday>,
    @SerializedName("requests")
    val requests: Requests,
    @SerializedName("status")
    val status: Int, // 200
    @SerializedName("warning")
    val warning: String // These results do not include state and province holidays. For more information, please visit https://holidayapi.com/docs
)