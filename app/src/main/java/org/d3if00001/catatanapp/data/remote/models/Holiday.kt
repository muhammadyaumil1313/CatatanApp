package org.d3if00001.catatanapp.data.remote.models


import com.google.gson.annotations.SerializedName

data class Holiday(
    @SerializedName("country")
    val country: String, // ID
    @SerializedName("date")
    val date: String, // 2022-01-01
    @SerializedName("name")
    val name: String, // New Year's Day
    @SerializedName("observed")
    val observed: String, // 2022-01-01
    @SerializedName("public")
    val `public`: Boolean, // true
    @SerializedName("uuid")
    val uuid: String, // e5252e5e-ad1e-4f9e-83d4-b126a72678ce
    @SerializedName("weekday")
    val weekday: Weekday
)