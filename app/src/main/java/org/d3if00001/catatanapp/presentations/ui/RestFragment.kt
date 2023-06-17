package org.d3if00001.catatanapp.presentations.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import org.d3if00001.catatanapp.R
import org.d3if00001.catatanapp.data.remote.api.ApiResponse
import org.d3if00001.catatanapp.data.remote.models.Holiday
import org.d3if00001.catatanapp.databinding.FragmentRestScreenBinding
import org.d3if00001.catatanapp.domain.repository.HolidayRepository
import org.d3if00001.catatanapp.presentations.ui.viewModels.HolidayViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.HolidayViewModelFactory
import org.d3if00001.catatanapp.presentations.ui.viewModels.NoteAddUpdateViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.viewModelFactory

class RestFragment : Fragment() {
    private lateinit var binding:FragmentRestScreenBinding
    private val listDataHoliday:ArrayList<Holiday> = ArrayList()
    private lateinit var  holidayViewModel: HolidayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentRestScreenBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val holidayRepository = HolidayRepository()
        val holidayViewModelFactory = HolidayViewModelFactory(holidayRepository)
        holidayViewModel = ViewModelProvider(this, holidayViewModelFactory).get(HolidayViewModel::class.java)

        holidayViewModel.getAllHolidayDays()
        initObserver()
        Log.d("list holiday","${listDataHoliday.size}")
    }
    private fun initObserver(){
        holidayViewModel.holidayResponse.observe(viewLifecycleOwner){
            when(it){
                is ApiResponse.Loading ->{
                    binding.pgRest.visibility = View.VISIBLE
                }
                is ApiResponse.Success->{
                    it.data.holidays.map { fragmentData->listDataHoliday.add(fragmentData)  }
                    binding.pgRest.visibility = View.GONE
                }
                is ApiResponse.Error->{
                    Toast.makeText(context,it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.pgRest.visibility = View.GONE
                }

                else -> {
                    binding.pgRest.visibility = View.GONE
                }
            }
        }
    }
}