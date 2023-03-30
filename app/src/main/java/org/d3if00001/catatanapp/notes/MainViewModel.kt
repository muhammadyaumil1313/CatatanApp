package org.d3if00001.catatanapp.notes

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.d3if00001.catatanapp.notes.entity.Note
import org.d3if00001.catatanapp.notes.repository.NoteRepository

class MainViewModel(application: Application): ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)
    fun getAllNotes(): LiveData<List<Note>> = mNoteRepository.getAllNotes()
}