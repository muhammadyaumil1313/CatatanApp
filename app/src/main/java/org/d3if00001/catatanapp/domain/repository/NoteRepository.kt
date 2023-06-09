package org.d3if00001.catatanapp.domain.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.data.local.NoteDao
import org.d3if00001.catatanapp.data.local.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val notesDao : NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        notesDao = db.noteDao()
    }

    fun getAllNotes() : LiveData<List<Note>> = notesDao.getAllNotes()
    fun insert(note: Note) = executorService.execute { notesDao.insert(note) }
    fun delete(note: Note) = executorService.execute { notesDao.delete(note) }
}