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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.d3if00001.catatanapp.R
import org.d3if00001.catatanapp.data.remote.api.ApiResponse
import org.d3if00001.catatanapp.data.remote.models.Holiday
import org.d3if00001.catatanapp.databinding.FragmentRestScreenBinding
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.domain.repository.HolidayRepository
import org.d3if00001.catatanapp.presentations.ui.viewModels.HolidayViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.HolidayViewModelFactory
import org.d3if00001.catatanapp.presentations.ui.viewModels.NoteAddUpdateViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.viewModelFactory

class RestFragment : Fragment() {
    private lateinit var binding:FragmentRestScreenBinding
    private val listDataHoliday:ArrayList<Holiday> = ArrayList()
    private lateinit var  holidayViewModel: HolidayViewModel
    private lateinit var adapter: HolidayAdapter
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

        adapter = HolidayAdapter()
        holidayViewModel = ViewModelProvider(this, holidayViewModelFactory).get(HolidayViewModel::class.java)
        holidayViewModel.getAllHolidayDays()
        initObserver()
        Glide.with(requireContext())
            .load("https://images.unsplash.com/photo-1546548729-772119f61058?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=812&q=80")
            .apply(RequestOptions().override(400))
            .into(binding.banner)
    }
    private fun initObserver(){
        holidayViewModel.holidayResponse.observe(viewLifecycleOwner){
            when(it){
                is ApiResponse.Loading ->{
                    binding.pgRest.visibility = View.VISIBLE
                }
                is ApiResponse.Success->{
                    it.data.holidays.map { fragmentData->
                        listDataHoliday.add(fragmentData)
                        adapter.setListHoliday(listDataHoliday)
                        binding.rvHoliday.layoutManager = LinearLayoutManager(activity)
                        binding.rvHoliday.setHasFixedSize(true)
                        binding.rvHoliday.adapter = adapter
                    }
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