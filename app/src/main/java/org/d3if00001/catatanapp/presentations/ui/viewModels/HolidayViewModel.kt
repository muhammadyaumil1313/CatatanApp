package org.d3if00001.catatanapp.presentations.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if00001.catatanapp.data.remote.api.ApiResponse
import org.d3if00001.catatanapp.data.remote.models.HolidayResponse
import org.d3if00001.catatanapp.domain.repository.HolidayRepository
import java.lang.Exception
import java.net.UnknownHostException

class HolidayViewModel(private val holidayRepository: HolidayRepository):ViewModel() {

    private var _holidayResponse: MutableLiveData<ApiResponse<HolidayResponse>> = MutableLiveData()
    val holidayResponse:LiveData<ApiResponse<HolidayResponse>> get() = _holidayResponse

    fun getAllHolidayDays(){
        viewModelScope.launch {
            try {
                _holidayResponse.postValue(ApiResponse.Loading)
                val client = holidayRepository.getAllHolidays()
                if( client.status != 200){
                    _holidayResponse.postValue(ApiResponse.Error(client.warning))
                }else{
                    _holidayResponse.postValue(ApiResponse.Success(client))
                }
            }catch (e:Exception){
                val errorMessage = if (e is UnknownHostException) {
                    "Koneksi terganggu. Periksa kembali koneksi internet Anda."
                } else {
                    e.message.toString()
                }
                _holidayResponse.postValue(ApiResponse.Error(errorMessage))
            }
        }
    }
}