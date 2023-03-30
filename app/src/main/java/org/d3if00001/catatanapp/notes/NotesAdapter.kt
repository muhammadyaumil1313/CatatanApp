package org.d3if00001.catatanapp.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.d3if00001.catatanapp.R
import org.d3if00001.catatanapp.databinding.ItemNotesBinding

class NotesAdapter(private val listNotes: ArrayList<Note>): RecyclerView.Adapter<NotesAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root){
        val noteTitle : TextView = binding.noteTitle
        val noteDate : TextView = binding.noteDate
        val noteDescription : TextView = binding.noteDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(title,body,_,date) = listNotes[position]
        holder.noteTitle.text = title
        holder.noteDescription.text = body.substring(0,100)
        holder.noteDate.text = date.toString()
    }
}