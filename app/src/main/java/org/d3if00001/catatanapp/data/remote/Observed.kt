package org.d3if00001.catatanapp.data.remote


import com.google.gson.annotations.SerializedName

data class Observed(
    @SerializedName("name")
    val name: String, // Saturday
    @SerializedName("numeric")
    val numeric: String // 6
)