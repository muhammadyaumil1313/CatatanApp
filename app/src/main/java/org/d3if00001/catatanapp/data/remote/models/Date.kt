package org.d3if00001.catatanapp.data.remote.models


import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("name")
    val name: String, // Saturday
    @SerializedName("numeric")
    val numeric: String // 6
)