package org.d3if00001.catatanapp.presentations.ui.helper

import androidx.recyclerview.widget.DiffUtil
import org.d3if00001.catatanapp.data.remote.models.Holiday

class HolidayDiffCallback(private val mOldHolidayList:List<Holiday>, private val newHolidayList:List<Holiday>)
    :DiffUtil.Callback(){
    override fun getOldListSize(): Int = mOldHolidayList.size

    override fun getNewListSize(): Int = newHolidayList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
    : Boolean = mOldHolidayList[oldItemPosition].uuid == newHolidayList[newItemPosition].uuid

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldHoliday= mOldHolidayList[oldItemPosition]
        val newHoliday = newHolidayList[newItemPosition]
        return oldHoliday.name == newHoliday.name && oldHoliday.date == newHoliday.date
    }

}