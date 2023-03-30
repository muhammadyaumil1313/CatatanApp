package org.d3if00001.catatanapp.notes.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.d3if00001.catatanapp.notes.entity.Note
import org.d3if00001.catatanapp.notes.database.NoteDao
import org.d3if00001.catatanapp.notes.database.NoteRoomDatabase
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
    fun update(note: Note) = executorService.execute { notesDao.update(note) }

}