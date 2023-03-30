package org.d3if00001.catatanapp.notes

import android.app.Application
import androidx.lifecycle.ViewModel
import org.d3if00001.catatanapp.notes.entity.Note
import org.d3if00001.catatanapp.notes.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel()  {
    private val mNoteRepository: NoteRepository = NoteRepository(application)
    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }
    fun update(note: Note) {
        mNoteRepository.update(note)
    }
    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}