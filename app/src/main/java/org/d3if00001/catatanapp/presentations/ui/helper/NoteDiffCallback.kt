package org.d3if00001.catatanapp.presentations.ui.helper

import androidx.recyclerview.widget.DiffUtil
import org.d3if00001.catatanapp.domain.models.Note

class NoteDiffCallback(private val mOldNoteList:List<Note>, private val newNoteList:List<Note>)
    :DiffUtil.Callback(){
    override fun getOldListSize(): Int = mOldNoteList.size

    override fun getNewListSize(): Int = newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
    : Boolean = mOldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = newNoteList[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.body == newEmployee.body
    }

}