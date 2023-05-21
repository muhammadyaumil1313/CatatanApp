package org.d3if00001.catatanapp.presentations.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if00001.catatanapp.databinding.ItemNotesBinding
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.presentations.ui.helper.NoteDiffCallback

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()
    inner class NoteViewHolder(private val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                noteTitle.text = note.title
                noteDate.text = note.date
                noteDescription.text = noteTitle.text.toString()
                noteCategory.text = note.category
                if(noteCategory.text.isEmpty()){
                    noteCategory.text = "Tidak ada Category"
                }
                cvItemNotes.setOnClickListener {
                    val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }
    fun setListNotes(listNotes: ArrayList<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }
}