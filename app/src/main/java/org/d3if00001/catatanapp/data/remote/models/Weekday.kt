package org.d3if00001.catatanapp.data.remote.models


import com.google.gson.annotations.SerializedName

data class Weekday(
    @SerializedName("date")
    val date: Date,
    @SerializedName("observed")
    val observed: Observed
)