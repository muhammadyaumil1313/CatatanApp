package org.d3if00001.catatanapp.domain.repository

import org.d3if00001.catatanapp.data.remote.api.ApiConfig
import org.d3if00001.catatanapp.data.remote.models.HolidayResponse

class HolidayRepository {
    suspend fun getAllHolidays(): HolidayResponse {
        return ApiConfig.getApiService().getAllHolidays()
    }
}