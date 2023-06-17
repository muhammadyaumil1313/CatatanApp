package org.d3if00001.catatanapp.data.remote.api

import org.d3if00001.catatanapp.data.remote.models.HolidayResponse
import retrofit2.http.GET

interface ApiService {
    @GET("holidays/?key=e783f881-6aca-4318-a053-af02ddfe90b7&country=ID&year=2022")
    suspend fun getAllHolidays():HolidayResponse
}