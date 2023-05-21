package org.d3if00001.catatanapp.presentations.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.domain.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel()  {
    private val mNoteRepository: NoteRepository = NoteRepository(application)
    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }
    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}