package org.d3if00001.catatanapp.presentations.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if00001.catatanapp.data.remote.models.Holiday
import org.d3if00001.catatanapp.data.remote.models.HolidayResponse
import org.d3if00001.catatanapp.databinding.ItemHolidayBinding
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.presentations.ui.helper.HolidayDiffCallback
import org.d3if00001.catatanapp.presentations.ui.helper.NoteDiffCallback

class HolidayAdapter: RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder>() {
    private val listHoliday = ArrayList<Holiday>()
    inner class HolidayViewHolder(private val binding:ItemHolidayBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(holiday: Holiday){
                with(binding){
                    holidayTitle.text = holiday.name
                    holidayDate.text = holiday.date
                }
            }
    }

    fun setListHoliday(listHoliday: ArrayList<Holiday>){
        val diffCallback = HolidayDiffCallback(this.listHoliday, listHoliday)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listHoliday.clear()
        this.listHoliday.addAll(listHoliday)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        return HolidayViewHolder(
            ItemHolidayBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = listHoliday.size

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int)
    = holder.bind(listHoliday[position])
}