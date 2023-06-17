package org.d3if00001.catatanapp.presentations.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if00001.catatanapp.domain.repository.HolidayRepository

class viewModelFactory private constructor(private val mApplication: Application)
    : ViewModelProvider.NewInstanceFactory(){
        companion object{
            @Volatile
            private var INSTANCE: viewModelFactory?=null
            @JvmStatic
            fun getInstance(application:Application): viewModelFactory {
                if (INSTANCE == null) {
                    synchronized(viewModelFactory::class.java) {
                        INSTANCE = viewModelFactory(application)
                    }
                }
                return INSTANCE as viewModelFactory
            }
        }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java)) {
            return NoteAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
class HolidayViewModelFactory(private val holidayRepository: HolidayRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HolidayViewModel::class.java)) {
            return HolidayViewModel(holidayRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
